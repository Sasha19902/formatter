package com.aleksander.formatter.formatterhundlers.fabrics;

import com.aleksander.formatter.formatterhundlers.fstate.SimpleFormatterStateTransfer;
import com.aleksander.formatter.formatterhundlers.fstate.StateRepository;
import com.aleksander.formatter.formatterhundlers.fstate.StateTransfer;
import com.aleksander.formatter.lexer.token.TokenRepository;
import com.aleksander.formatter.utils.SpecialKeys;

public class StateTransferFabricImpl implements StateTransferFabric {

    @Override
    public StateTransfer generateStateTransfer(SpecialKeys specialKeys, TokenRepository tokenRepository, StateRepository stateRepository) {
        return new SimpleFormatterStateTransfer(stateRepository);
    }
}
