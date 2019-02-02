package com.aleksander.formatter.formatterhundlers.exceptions;

public class FormatterException extends Exception {

    public FormatterException() {
    }

    public FormatterException(String message) {
        super(message);
    }

    public FormatterException(String message, Throwable cause) {
        super(message, cause);
    }

    public FormatterException(Throwable cause) {
        super(cause);
    }
}
