package com.alexoo.foro_hub.foro_hub.domain.topico;

import com.alexoo.foro_hub.foro_hub.domain.curso.Curso;
import com.alexoo.foro_hub.foro_hub.domain.curso.CursoListarDTO;
import com.alexoo.foro_hub.foro_hub.domain.usuario.Usuario;
import com.alexoo.foro_hub.foro_hub.domain.usuario.UsuarioListarDTO;

import java.time.LocalDateTime;

public record TopicoListarDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String estado,
        UsuarioListarDTO autor,
        CursoListarDTO curso
) {
    public TopicoListarDTO(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getEstado().toString(),
                new UsuarioListarDTO(topico.getAutor()),
                new CursoListarDTO(topico.getCurso())
        );
    }
}
