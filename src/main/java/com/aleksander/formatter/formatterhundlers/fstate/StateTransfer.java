package com.aleksander.formatter.formatterhundlers.fstate;

import com.aleksander.formatter.utils.Token;

public interface StateTransfer {
    FormatterState nextState(Token token, FormatterState currentState);
    FormatterState startedFormatterState();
}
