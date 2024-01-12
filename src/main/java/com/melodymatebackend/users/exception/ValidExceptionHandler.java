package com.melodymatebackend.users.exception;

import com.melodymatebackend.common.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ValidExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse> handleValidationException(ConstraintViolationException e) {
        Map<String, String> errors = new HashMap<>();
        String fieldName = e.getMessage().replace("signup.arg0.", "");
        String errorMessage = e.getMessage();
        errors.put(fieldName, errorMessage);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), errorMessage), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        Map<String, String> errors = new HashMap<>();
        String fieldName = e.getMessage().replace("signup.arg0.", "");
        String errorMessage = e.getMessage();
        errors.put(fieldName, errorMessage);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.name(), errorMessage), HttpStatus.CONFLICT);
    }
}