package com.aleksander.formatter.fstate;

import com.aleksander.lexer.Token;

public class SimpleFormatterStateTransfer implements StateTransfer {

    private StateRepository stateRepository;

    @Override
    public FormatterState nextState(Token token, FormatterState currentState) {
        return null;
    }
}
