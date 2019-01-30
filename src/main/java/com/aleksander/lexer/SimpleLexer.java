package com.aleksander.lexer;

import com.aleksander.iohundlers.Reader;

public class SimpleLexer implements Lexer {

    private Reader reader;

    public SimpleLexer(Reader reader) {
        this.reader = reader;
    }

    @Override
    public Token nextToken() {
        return null;
    }

    @Override
    public boolean hasNextToken() {
        return false;
    }
}
