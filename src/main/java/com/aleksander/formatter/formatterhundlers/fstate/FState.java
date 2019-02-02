package com.aleksander.formatter.formatterhundlers.fstate;

import java.util.Objects;

public class FState implements FormatterState {

    private String info;

    public FState(String info) {
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
        FState fState = (FState) o;
        return Objects.equals(info, fState.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(info);
    }
}
