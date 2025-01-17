package com.alexoo.foro_hub.foro_hub.domain.topico.validacion;

import com.alexoo.foro_hub.foro_hub.domain.ValidationException;
import com.alexoo.foro_hub.foro_hub.domain.topico.Topico;
import com.alexoo.foro_hub.foro_hub.domain.usuario.Usuario;
import com.alexoo.foro_hub.foro_hub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ValidacionEliminarTopicoPorAutorDeTopico implements ValidacionEliminarTopico {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void validar(Topico topico) {
        if(!esAutorDelTopico(topico)){
            throw new ValidationException("El usuario autenticado no es el autor del tópico");
        }
    }

    private boolean esAutorDelTopico(Topico topico) {
        Usuario usuarioAutenticado = obtenerUsuarioAutenticado();
        return topico.getAutor().equals(usuarioAutenticado);
    }

    private Usuario obtenerUsuarioAutenticado() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return (Usuario) usuarioRepository.findByEmail(username);
    }
}
