package com.melodymatebackend.common.exception;

import com.melodymatebackend.common.ApiResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiControllerAdvice {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> handleApiException(ApiException e) {
        ApiResponse response = new ApiResponse(e.getStatus(), e.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(e.getStatus()));
    }
}
