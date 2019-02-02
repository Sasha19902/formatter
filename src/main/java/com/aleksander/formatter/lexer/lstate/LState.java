package com.aleksander.formatter.lexer.lstate;

import java.util.Objects;

public class LState implements LexerState {

    private String info;

    public LState(String info) {
        this.info = info;
    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LState lState = (LState) o;
        return Objects.equals(info, lState.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(info);
    }
}
