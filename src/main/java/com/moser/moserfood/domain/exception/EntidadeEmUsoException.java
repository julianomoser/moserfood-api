package com.moser.moserfood.domain.exception;

/**
 * @author Juliano Moser
 */
public class EntidadeEmUsoException extends RuntimeException {

    private static final long serialVersionUID = 1;

    public EntidadeEmUsoException(String message) {
        super(message);
    }
}
