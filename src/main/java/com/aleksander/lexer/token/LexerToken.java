package com.aleksander.lexer.token;

public class LexerToken implements Token {

    private String content;
    private String type;

    public LexerToken(String content, String type) {
        this.content = content;
        this.type = type;
    }

    @Override
    public String getContent() {
        return content;
    }

    public String getType() {
        return type;
    }
}
