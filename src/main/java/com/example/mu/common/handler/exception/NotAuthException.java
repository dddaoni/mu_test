package com.example.mu.common.handler.exception;

/**
 * Not Auth Exception
 */
public class NotAuthException extends RuntimeException {

    public NotAuthException() {
        super();
    }

    public NotAuthException(String message) {
        super(message);
    }
}
