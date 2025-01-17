package com.alexoo.foro_hub.foro_hub.infra.errores;

import com.alexoo.foro_hub.foro_hub.domain.ValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import com.alexoo.foro_hub.foro_hub.infra.ErrorDTO;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControlExcepciones {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity NotFoundHandler(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity Error400(MethodArgumentNotValidException error){
        var mensaje= error.getFieldErrors().stream().map(ErrorDTO::new).toList();
        return ResponseEntity.badRequest().body(mensaje);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity tratarErrorDeValidacion(ValidationException error){
        return ResponseEntity.badRequest().body(error.getMessage());
    }

}