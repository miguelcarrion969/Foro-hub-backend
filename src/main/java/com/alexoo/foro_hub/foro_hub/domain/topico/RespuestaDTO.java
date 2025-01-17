package com.alexoo.foro_hub.foro_hub.domain.topico;

public record RespuestaDTO(
        Long id,
        String mensaje,
        Boolean solucion,
        String autorNombre
) {
    public RespuestaDTO(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getSolucion(), respuesta.getAutor().getNombre());
    }
}
