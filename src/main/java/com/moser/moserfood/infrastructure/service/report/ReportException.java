package com.moser.moserfood.infrastructure.service.report;

/**
 * @author Juliano Moser
 */
public class ReportException extends RuntimeException {

    private static final long serialVersionUID = 1;

    public ReportException(String message) {
        super(message);
    }

    public ReportException(String message, Throwable cause) {
        super(message, cause);
    }
}
