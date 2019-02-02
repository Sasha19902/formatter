package com.aleksander.formatter.lexer;

import com.aleksander.formatter.iohundlers.Reader;
import com.aleksander.formatter.lexer.commands.LexerCommander;
import com.aleksander.formatter.lexer.fabrics.LexerCommanderFabricImpl;
import com.aleksander.formatter.lexer.fabrics.StateTransferFabricImpl;
import com.aleksander.formatter.lexer.lstate.StateTransfer;
import com.aleksander.formatter.lexer.token.TokenBuilder;
import com.aleksander.formatter.lexer.token.TokenRepository;
import com.aleksander.formatter.utils.SpecialKeys;

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
