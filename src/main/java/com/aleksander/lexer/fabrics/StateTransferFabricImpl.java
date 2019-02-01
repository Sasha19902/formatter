package com.aleksander.lexer.fabrics;

import com.aleksander.lexer.lstate.SimpleLexerStateTransfer;
import com.aleksander.lexer.lstate.StateRepository;
import com.aleksander.lexer.lstate.StateTransfer;
import com.aleksander.utils.SpecialKeys;

public class StateTransferFabricImpl implements StateTransferFabric {

    @Override
    public StateTransfer generateStateTransfer(SpecialKeys specialKeys) {
        StateRepository stateRepository = new StateRepository(specialKeys.getKeySymbols());
        return new SimpleLexerStateTransfer(stateRepository);
    }
}
