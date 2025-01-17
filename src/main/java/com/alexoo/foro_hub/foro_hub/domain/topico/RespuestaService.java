package com.alexoo.foro_hub.foro_hub.domain.topico;

import com.alexoo.foro_hub.foro_hub.domain.ValidationException;
import com.alexoo.foro_hub.foro_hub.domain.usuario.Usuario;
import com.alexoo.foro_hub.foro_hub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RespuestaService {
    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    public Respuesta responder(RespuestaIngresoDTO datos){
        Usuario usuario = obtenerUsuarioAutenticado();
        var respuesta = new Respuesta(datos);
        if(datos.respuestaPadre_id()!=null){
            var respuestaPadre = respuestaRepository.findById(datos.respuestaPadre_id())
                    .orElseThrow(() -> new ValidationException("Respuesta padre no encontrada"));
            respuesta.setRespuestaPadre(respuestaPadre);
        }
        respuesta.setAutor(usuario);
        var topico = topicoRepository.findById(datos.topico_id())
                .orElseThrow(() -> new ValidationException("Tópico no encontrado"));
        respuesta.setTopico(topico);
        respuestaRepository.save(respuesta);
        return respuesta;
    }

    public RespuestaDTO actualizar(RespuestaActualizarDTO datos) {
        var respuesta = respuestaRepository.findById(datos.id())
                .orElseThrow(() -> new ValidationException("Respuesta no encontrada"));
        if(datos.mensaje()!=null){
            respuesta.setMensaje(datos.mensaje());
        }
        if(datos.solucion()!=null){
            respuesta.setSolucion(datos.solucion());
        }
        respuestaRepository.save(respuesta);
        return new RespuestaDTO(respuestaRepository.getReferenceById(datos.id()));
    }

    private Usuario obtenerUsuarioAutenticado() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return (Usuario) usuarioRepository.findByEmail(username);
    }
}
