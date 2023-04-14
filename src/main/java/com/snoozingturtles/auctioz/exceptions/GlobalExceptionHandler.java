package com.snoozingturtles.auctioz.exceptions;

import com.snoozingturtles.auctioz.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse> entityNotFoundExceptionHandler(EntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body
                (
                        ApiResponse.builder()
                                .message(exception.getMessage())
                                .success(false)
                                .build()
                );
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ApiResponse> loginExceptionHandler(LoginException exception) {
        return ResponseEntity.badRequest().body(
                ApiResponse.builder()
                        .message(exception.getMessage())
                        .success(false)
                        .build());
    }
}
