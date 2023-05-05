package com.dhruza.creditcardapplicationapi.exception;

public class ActiveApplicationFileException extends RuntimeException {
    public ActiveApplicationFileException() {
        super();
    }

    public ActiveApplicationFileException(String message) {
        super(message);
    }

    public ActiveApplicationFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
