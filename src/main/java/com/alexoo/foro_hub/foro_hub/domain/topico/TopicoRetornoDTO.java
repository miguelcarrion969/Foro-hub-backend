package com.alexoo.foro_hub.foro_hub.domain.topico;

public record TopicoRetornoDTO(
        Long id,
        String titulo,
        String mensaje,
        String curso
) {
    public TopicoRetornoDTO(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getCurso().getNombre()
        );
    }
}
