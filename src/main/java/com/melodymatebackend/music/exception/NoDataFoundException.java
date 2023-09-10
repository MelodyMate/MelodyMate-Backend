package com.melodymatebackend.music.exception;

public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException(String noDataFound) {
        super(noDataFound);
    }
}
