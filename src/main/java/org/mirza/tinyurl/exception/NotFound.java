package org.mirza.tinyurl.exception;

public class NotFound extends RuntimeException {
    public NotFound(String message) {
        super(message);
    }
}
