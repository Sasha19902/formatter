package com.aleksander.formatter.lexer.token;

import com.aleksander.formatter.utils.Token;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LexerToken that = (LexerToken) o;
        return Objects.equals(content, that.content) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, type);
    }
}
