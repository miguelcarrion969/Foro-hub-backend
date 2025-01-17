package com.alexoo.foro_hub.foro_hub.domain;

public class ValidationException extends RuntimeException {
    public ValidationException(String mensaje) {
        super(mensaje);
    }
}
