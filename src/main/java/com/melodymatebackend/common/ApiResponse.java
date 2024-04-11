package com.melodymatebackend.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
    private int status;
    private String message;
    private Object data;

    private String errorMessage;

    public ApiResponse(int status, String message, String errorMessage) {
        this.status = status;
        this.message = message;
        this.errorMessage = errorMessage;
    }
}
