package com.aleksander.lexer.lstate;

public interface StateTransfer {
    LexerState nextState(char smb, LexerState currentState);
    LexerState startedLexerState();
    StateRepository getRepository();
}
