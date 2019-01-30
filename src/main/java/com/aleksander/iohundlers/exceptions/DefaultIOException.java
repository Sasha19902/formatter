package com.aleksander.iohundlers.exceptions;

public class DefaultIOException extends Exception {

    public DefaultIOException() {

    }

    public DefaultIOException(String message) {
        super(message);
    }

    public DefaultIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DefaultIOException(Throwable cause) {
        super(cause);
    }
}
