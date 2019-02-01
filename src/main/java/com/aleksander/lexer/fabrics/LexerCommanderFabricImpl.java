package com.aleksander.lexer.fabrics;

import com.aleksander.lexer.commands.LexerCommandRepository;
import com.aleksander.lexer.commands.LexerCommander;
import com.aleksander.lexer.commands.SimpleLexerCommander;
import com.aleksander.lexer.lstate.StateRepository;
import com.aleksander.lexer.token.TokenBuilder;

public class LexerCommanderFabricImpl implements LexerCommanderFabric {



    @Override
    public LexerCommander generateLexerCommander(TokenBuilder tokenBuilder, StateRepository stateRepository) {
        LexerCommandRepository lexerCommandRepository = new LexerCommandRepository(tokenBuilder, stateRepository);
        return new SimpleLexerCommander(lexerCommandRepository);
    }
}
