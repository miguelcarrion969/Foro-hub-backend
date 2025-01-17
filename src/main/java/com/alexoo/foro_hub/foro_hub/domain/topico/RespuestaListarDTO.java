package com.alexoo.foro_hub.foro_hub.domain.topico;

import java.util.List;

public record RespuestaListarDTO(
        Long id,
        String mensaje,
        String autor,
        Boolean solucion,
        List<RespuestaListarDTO> respuestasHijas
) {
    public RespuestaListarDTO(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getAutor().getNombre(),
                respuesta.getSolucion(),
                respuesta.getRespuestasHijas() != null
                        ? respuesta.getRespuestasHijas().stream().map(RespuestaListarDTO::new).toList()
                        : List.of()
        );
    }
}

