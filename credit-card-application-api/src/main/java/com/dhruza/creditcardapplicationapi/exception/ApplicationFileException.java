package com.dhruza.creditcardapplicationapi.exception;

public class ApplicationFileException extends RuntimeException {
    public ApplicationFileException() {
        super();
    }

    public ApplicationFileException(String message) {
        super(message);
    }

    public ApplicationFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
