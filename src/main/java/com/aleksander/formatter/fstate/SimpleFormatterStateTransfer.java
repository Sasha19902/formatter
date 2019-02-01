package com.aleksander.formatter.fstate;

import com.aleksander.lexer.token.Token;

public class SimpleFormatterStateTransfer implements StateTransfer {

    private StateRepository stateRepository;

    @Override
    public FormatterState nextState(Token token, FormatterState currentState) {
        return null;
    }

    @Override
    public FormatterState startedFormatterState() {
        return null;
    }
}
