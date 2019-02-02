package com.aleksander.formatter.formatterhundlers.fabrics;

import com.aleksander.formatter.formatterhundlers.fstate.StateRepository;
import com.aleksander.formatter.formatterhundlers.fstate.StateTransfer;
import com.aleksander.formatter.lexer.token.TokenRepository;
import com.aleksander.formatter.utils.SpecialKeys;

public interface StateTransferFabric {
    StateTransfer generateStateTransfer(SpecialKeys specialKeys, TokenRepository tokenRepository, StateRepository stateRepository);
}
