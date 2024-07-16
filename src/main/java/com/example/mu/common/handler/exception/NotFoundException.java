package com.example.mu.common.handler.exception;

/**
 * Not Found Exception
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }
}
