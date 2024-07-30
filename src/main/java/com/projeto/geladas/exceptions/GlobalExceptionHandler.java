package com.projeto.geladas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourcesNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourcesNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento não encontrado");
    }
    
}
