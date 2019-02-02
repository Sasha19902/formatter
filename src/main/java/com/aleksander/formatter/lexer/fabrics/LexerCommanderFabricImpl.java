package com.aleksander.formatter.lexer.fabrics;

import com.aleksander.formatter.lexer.commands.LexerCommandRepository;
import com.aleksander.formatter.lexer.commands.LexerCommander;
import com.aleksander.formatter.lexer.commands.SimpleLexerCommander;
import com.aleksander.formatter.lexer.lstate.StateRepository;
import com.aleksander.formatter.lexer.token.TokenBuilder;

public class LexerCommanderFabricImpl implements LexerCommanderFabric {



    @Override
    public LexerCommander generateLexerCommander(TokenBuilder tokenBuilder, StateRepository stateRepository) {
        LexerCommandRepository lexerCommandRepository = new LexerCommandRepository(tokenBuilder, stateRepository);
        return new SimpleLexerCommander(lexerCommandRepository);
    }
}
