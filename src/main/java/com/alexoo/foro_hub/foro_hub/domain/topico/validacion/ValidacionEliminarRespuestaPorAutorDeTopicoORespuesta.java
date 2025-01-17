package com.alexoo.foro_hub.foro_hub.domain.topico.validacion;

import com.alexoo.foro_hub.foro_hub.domain.ValidationException;
import com.alexoo.foro_hub.foro_hub.domain.topico.Respuesta;
import com.alexoo.foro_hub.foro_hub.domain.topico.Topico;
import com.alexoo.foro_hub.foro_hub.domain.topico.TopicoRepository;
import com.alexoo.foro_hub.foro_hub.domain.usuario.Usuario;
import com.alexoo.foro_hub.foro_hub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ValidacionEliminarRespuestaPorAutorDeTopicoORespuesta implements ValidacionEliminarRespuesta {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validar(Respuesta respuesta) {
        if(!esAutorDeRespuesta(respuesta)){
            throw new ValidationException("El usuario autenticado no es el autor de la respuesta o del topico");
        }
    }

    private boolean esAutorDeRespuesta(Respuesta respuesta) {
        Usuario usuarioAutenticado = obtenerUsuarioAutenticado();
        if(respuesta.getAutor().equals(usuarioAutenticado)){
            return true;
        }
            var idTopico = respuesta.getTopico().getId();
            var topico = topicoRepository.getReferenceById(idTopico);
            return topico.getAutor().equals(usuarioAutenticado);
    }

    private Usuario obtenerUsuarioAutenticado() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return (Usuario) usuarioRepository.findByEmail(username);
    }
}
