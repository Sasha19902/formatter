package com.aleksander.lexer;

import com.aleksander.lexer.exceptions.LexerException;
import com.aleksander.lexer.token.Token;

public interface Lexer {
    Token nextToken() throws LexerException;
    boolean hasNextToken() throws LexerException;
}
