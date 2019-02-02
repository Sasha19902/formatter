package com.aleksander.formatter.lexer;

import com.aleksander.formatter.lexer.exceptions.LexerException;
import com.aleksander.formatter.utils.Token;

public interface Lexer {
    Token nextToken() throws LexerException;
    boolean hasNextToken() throws LexerException;
}
