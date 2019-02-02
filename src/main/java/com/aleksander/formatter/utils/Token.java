package com.aleksander.formatter.utils;

//TT - token type
public interface Token {
    String TT_DEFAULT = "KEY_DEFAULT";

    String getContent();
    String getType();
}
