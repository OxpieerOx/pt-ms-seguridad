package com.intech.msseguridad.exceptions;

public class InternalServerErrorException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public InternalServerErrorException() {
        super();
    }

    public InternalServerErrorException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InternalServerErrorException(final String message) {
        super(message);
    }

    public InternalServerErrorException(final Throwable cause) {
        super(cause);
    }
}