package com.dhruza.creditcardapplicationapi.exception;

public class DataDoesNotExistException extends RuntimeException {

    public DataDoesNotExistException() {
        super();
    }

    public DataDoesNotExistException(String message) {
        super(message);
    }

    public DataDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
