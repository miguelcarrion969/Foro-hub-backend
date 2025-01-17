package com.alexoo.foro_hub.foro_hub.domain.topico.validacion;

import com.alexoo.foro_hub.foro_hub.domain.ValidationException;
import com.alexoo.foro_hub.foro_hub.domain.curso.CursoRepository;
import com.alexoo.foro_hub.foro_hub.domain.topico.*;
import com.alexoo.foro_hub.foro_hub.domain.usuario.Usuario;
import com.alexoo.foro_hub.foro_hub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private List<ValidacionEliminarTopico> validaciones;

    public Topico crear(TopicoIngresoDTO datos){
        var curso = cursoRepository.findById(datos.curso_id())
                .orElseThrow(() -> new ValidationException("Curso no encontrado"));
        var usuarioAutenticado = obtenerUsuarioAutenticado();
        var topico = new Topico(datos);
        topico.setAutor(usuarioAutenticado);
        topico.setCurso(curso);
        topicoRepository.save(topico);
        return topico;
    }

    private Usuario obtenerUsuarioAutenticado() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return (Usuario) usuarioRepository.findByEmail(username);
    }

    public Topico actualizar(TopicoActualizarDTO datos){
        var topico = topicoRepository.BuscarPorId(datos.id())
                .orElseThrow(() -> new ValidationException("Tópico no encontrado"));

        if(datos.titulo()!=null)
            topico.setTitulo(datos.titulo());

        if(datos.mensaje()!=null)
            topico.setMensaje(datos.mensaje());

        if(datos.estado()!=null)
            topico.setEstado(datos.estado());
        return topico;
    }

    public void eliminar(Long id){
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Tópico no encontrado"));
        // Validaciones para eliminar
        validaciones.forEach(v->v.validar(topico));
        topicoRepository.delete(topico);
    }
}
