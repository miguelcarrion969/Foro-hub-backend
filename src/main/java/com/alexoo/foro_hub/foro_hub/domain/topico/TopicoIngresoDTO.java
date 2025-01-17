package com.alexoo.foro_hub.foro_hub.domain.topico;

import com.alexoo.foro_hub.foro_hub.domain.curso.Curso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoIngresoDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        Estado estado,
        @NotNull
        Long curso_id
) {
}
