package com.aleksander.formatter.fstate;

import com.aleksander.lexer.token.Token;

public interface StateTransfer {
    FormatterState nextState(Token token, FormatterState currentState);
    FormatterState startedFormatterState();
}
