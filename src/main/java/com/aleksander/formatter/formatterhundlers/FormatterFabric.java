package com.aleksander.formatter.formatterhundlers;

import com.aleksander.formatter.formatterhundlers.fabrics.FormatterCommanderFabricImpl;
import com.aleksander.formatter.formatterhundlers.fabrics.StateTransferFabricImpl;
import com.aleksander.formatter.formatterhundlers.fstate.StateRepository;
import com.aleksander.formatter.lexer.token.TokenRepository;
import com.aleksander.formatter.utils.SpecialKeys;
import com.aleksander.formatter.utils.Token;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class FormatterFabric {
    public Formatter getFormatter() throws IOException {
        SpecialKeys specialKeys = new SpecialKeys();
        TokenRepository tokenRepository = new TokenRepository(specialKeys);
        StateRepository stateRepository = new StateRepository(specialKeys, tokenRepository);
        StateTransferFabricImpl stateTransferFabric = new StateTransferFabricImpl();
        FormatterCommanderFabricImpl formatterCommanderFabric = new FormatterCommanderFabricImpl();
        Deque<Token> buffer = new ArrayDeque<>();

        return new SimpleFormatter(stateTransferFabric.generateStateTransfer(specialKeys, tokenRepository, stateRepository), formatterCommanderFabric.generateFormatterCommander(buffer, stateRepository), buffer);
    }
}
