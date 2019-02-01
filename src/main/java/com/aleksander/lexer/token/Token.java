package com.aleksander.lexer.token;

//TT - token type
public interface Token {
    public static final String TT_KEY_WORD = "KEY_WORD";
    public static final String TT_KEY_SYMBOL = "KEY_SYMBOL";
    public static final String TT_DEFAULT = "KEY_DEFAULT";

    String getContent();
    String getType();
}
