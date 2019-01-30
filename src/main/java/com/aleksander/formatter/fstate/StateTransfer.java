package com.aleksander.formatter.fstate;

import com.aleksander.lexer.Token;

public interface StateTransfer {
    FormatterState nextState(Token token, FormatterState currentState);
    FormatterState startedFormatterState();
}
