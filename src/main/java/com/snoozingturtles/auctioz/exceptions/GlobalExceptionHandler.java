package com.snoozingturtles.auctioz.exceptions;

import com.snoozingturtles.auctioz.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .message(exception.getMessage())
                        .success(false)
                        .build(), HttpStatus.UNAUTHORIZED);
    }

    //handling validations
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        Map<String, String> response = new HashMap<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(fieldError -> response.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
