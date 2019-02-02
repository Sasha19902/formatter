package com.aleksander.formatter.lexer.exceptions;

public class LexerException extends Exception {

    public LexerException() {
    }

    public LexerException(String message) {
        super(message);
    }

    public LexerException(String message, Throwable cause) {
        super(message, cause);
    }

    public LexerException(Throwable cause) {
        super(cause);
    }
}
