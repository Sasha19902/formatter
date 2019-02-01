package com.aleksander.formatter.fstate;

public class FState implements FormatterState {

    private String info;

    public FState(String info) {
        this.info = info;
    }

    @Override
    public String getInfo() {
        return info;
    }
}
