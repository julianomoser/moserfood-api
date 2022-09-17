package com.moser.moserfood.infrastructure.service.sms;

/**
 * @author Juliano Moser
 */
public class SmslException extends RuntimeException {

    public SmslException(String message) {
        super(message);
    }

    public SmslException(String message, Throwable cause) {
        super(message, cause);
    }
}
