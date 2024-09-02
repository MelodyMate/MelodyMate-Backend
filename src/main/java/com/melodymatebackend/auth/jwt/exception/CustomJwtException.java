package com.melodymatebackend.auth.jwt.exception;

public class CustomJwtException extends RuntimeException {

    public CustomJwtException(String message) {
        super(message);
    }
}
