package com.alexoo.foro_hub.foro_hub.infra;

import org.springframework.validation.FieldError;

public record ErrorDTO(String error, String mensaje) {
    public ErrorDTO(FieldError fieldError) {
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
