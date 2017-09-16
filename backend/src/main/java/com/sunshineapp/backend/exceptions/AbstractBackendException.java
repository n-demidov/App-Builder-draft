package com.sunshineapp.backend.exceptions;

public class AbstractBackendException extends RuntimeException {

    public AbstractBackendException() {
        super();
    }

    public AbstractBackendException(String message) {
        super(message);
    }

    public AbstractBackendException(Throwable cause) {
        super(cause);
    }

    public AbstractBackendException(String message, Throwable cause) {
        super(message, cause);
    }

}
