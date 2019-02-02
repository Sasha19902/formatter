package com.aleksander.formatter.utils;

import java.util.Objects;

public class Pair<T,U> {

    private T fType;
    private U sType;

    public Pair(T fType, U sType) {
        this.fType = fType;
        this.sType = sType;
    }

    public T getfType() {
        return fType;
    }

    public void setfType(T fType) {
        this.fType = fType;
    }

    public U getsType() {
        return sType;
    }

    public void setsType(U sType) {
        this.sType = sType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(fType, pair.fType) &&
                Objects.equals(sType, pair.sType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fType, sType);
    }
}
