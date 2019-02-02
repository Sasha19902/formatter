package com.aleksander.formatter.formatterhundlers;

import com.aleksander.formatter.utils.Token;

import java.util.Objects;

public class FormatterToken implements Token {

    private String content;
    private String type;

    public FormatterToken(String content, String type) {
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
        FormatterToken that = (FormatterToken) o;
        return Objects.equals(content, that.content) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, type);
    }
}
