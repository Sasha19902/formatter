package com.aleksander.formatter.formatterhundlers.fstate;

import com.aleksander.formatter.utils.Token;

public class SimpleFormatterStateTransfer implements StateTransfer {

    private StateRepository stateRepository;

    public SimpleFormatterStateTransfer(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public FormatterState nextState(Token token, FormatterState currentState) {
        return stateRepository.getFormatterState(currentState, token);
    }

    @Override
    public FormatterState startedFormatterState() {
        return stateRepository.getStateByIdentificator(StateRepository.StateIdentificator.DEFAULT_STATE);
    }
}
