package com.aleksander.lexer.fabrics;

import com.aleksander.lexer.lstate.StateTransfer;

public interface StateTransferFabric {
    StateTransfer generateStateTransfer();
}
