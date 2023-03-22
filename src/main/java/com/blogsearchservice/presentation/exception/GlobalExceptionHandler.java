package com.blogsearchservice.presentation.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorReponse> handleConstraintViolationException(ConstraintViolationException exception) {
        ErrorReponse errorReponse = new ErrorReponse(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.badRequest().body(errorReponse);
    }
}
