package com.aleksander.lexer.fabrics;

import com.aleksander.lexer.lstate.StateTransfer;
import com.aleksander.utils.SpecialKeys;

public interface StateTransferFabric {
    StateTransfer generateStateTransfer(SpecialKeys specialKeys);
}
