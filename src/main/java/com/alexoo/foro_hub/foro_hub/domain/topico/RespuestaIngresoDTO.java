package com.alexoo.foro_hub.foro_hub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RespuestaIngresoDTO(
        @NotBlank
        String mensaje,
        @NotNull
        Long topico_id,
        Long respuestaPadre_id
) {
}
