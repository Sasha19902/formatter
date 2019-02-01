package com.aleksander.lexer;

import com.aleksander.iohundlers.Reader;
import com.aleksander.lexer.commands.LexerCommander;
import com.aleksander.lexer.fabrics.LexerCommanderFabricImpl;
import com.aleksander.lexer.fabrics.StateTransferFabricImpl;
import com.aleksander.lexer.lstate.StateTransfer;
import com.aleksander.lexer.token.TokenBuilder;
import com.aleksander.lexer.token.TokenRepository;
import com.aleksander.utils.SpecialKeys;

import java.io.IOException;

public class LexerFabric {

    public Lexer generateLexer(Reader reader) throws IOException {
        SpecialKeys specialKeys = new SpecialKeys();
        TokenRepository tokenRepository = new TokenRepository(specialKeys);
        TokenBuilder tokenBuilder = new TokenBuilder(tokenRepository);
        StateTransfer stateTransfer = new StateTransferFabricImpl().generateStateTransfer(specialKeys);
        LexerCommander lexerCommander = new LexerCommanderFabricImpl().generateLexerCommander(tokenBuilder, stateTransfer.getRepository());

        return new SimpleLexer(reader, tokenBuilder, stateTransfer, lexerCommander);
    }

}
