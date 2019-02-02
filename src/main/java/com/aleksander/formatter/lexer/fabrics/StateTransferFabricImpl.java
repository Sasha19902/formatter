package com.aleksander.formatter.lexer.fabrics;

import com.aleksander.formatter.lexer.lstate.SimpleLexerStateTransfer;
import com.aleksander.formatter.lexer.lstate.StateRepository;
import com.aleksander.formatter.lexer.lstate.StateTransfer;
import com.aleksander.formatter.utils.SpecialKeys;

public class StateTransferFabricImpl implements StateTransferFabric {

    @Override
    public StateTransfer generateStateTransfer(SpecialKeys specialKeys) {
        StateRepository stateRepository = new StateRepository(specialKeys.getKeySymbols());
        return new SimpleLexerStateTransfer(stateRepository);
    }
}
