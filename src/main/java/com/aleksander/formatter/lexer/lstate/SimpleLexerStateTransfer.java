package com.aleksander.formatter.lexer.lstate;

public class SimpleLexerStateTransfer implements StateTransfer {

    private static final int ZERO_MEMORY = 0;
    private StateRepository stateRepository;
    private StringBuilder stringBuilder;


    public SimpleLexerStateTransfer(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
        stringBuilder = new StringBuilder();
    }

    @Override
    public LexerState nextState(char smb, LexerState currentState) {
        stringBuilder.append(smb);
        String res = stringBuilder.toString();
        stringBuilder.setLength(ZERO_MEMORY);
        return stateRepository.getState(currentState, res);
    }

    @Override
    public LexerState startedLexerState() {
        return stateRepository.getStateByIdentificator(StateRepository.StateIdentificator.DEFAULT_STATE);
    }

    @Override
    public StateRepository getRepository() {
        return stateRepository;
    }
}
