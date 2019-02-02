package com.aleksander.formatter.lexer.fabrics;

import com.aleksander.formatter.lexer.lstate.StateTransfer;
import com.aleksander.formatter.utils.SpecialKeys;

public interface StateTransferFabric {
    StateTransfer generateStateTransfer(SpecialKeys specialKeys);
}
