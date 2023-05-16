package com.maslyna.springemailservice.util.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    private ResponseEntity<?> responseStatusExceptionHandler(ResponseStatusException exception) {
        return ResponseEntity.status(exception.getStatusCode()).body(exception.getBody());
    }
}
