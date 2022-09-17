package com.moser.moserfood.infrastructure.service.email;

/**
 * @author Juliano Moser
 */
public class EmailException extends RuntimeException {

    public EmailException(String message) {
        super(message);
    }

    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
