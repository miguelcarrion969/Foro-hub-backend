package com.alexoo.foro_hub.foro_hub.domain.topico;

import jakarta.validation.constraints.NotNull;

public record RespuestaActualizarDTO(
        @NotNull
        Long id,
        String mensaje,
        Boolean solucion
) {
}
