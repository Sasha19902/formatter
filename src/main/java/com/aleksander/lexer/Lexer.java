package com.aleksander.lexer;

import com.aleksander.lexer.exceptions.LexerException;

public interface Lexer {
    Token nextToken() throws LexerException;
    boolean hasNextToken() throws LexerException;
}
