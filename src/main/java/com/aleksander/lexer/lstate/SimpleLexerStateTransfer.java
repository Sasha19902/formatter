package com.aleksander.lexer.lstate;

import com.aleksander.formatter.fstate.FormatterState;

public class SimpleLexerStateTransfer implements StateTransfer {
    @Override
    public LexerState nextState(char smb, FormatterState currentState) {
        return null;
    }

    @Override
    public LexerState startedLexerState() {
        return null;
    }
}
