package com.aleksander.lexer.fabrics;

import com.aleksander.lexer.commands.LexerCommander;
import com.aleksander.lexer.lstate.StateRepository;
import com.aleksander.lexer.token.TokenBuilder;

public interface LexerCommanderFabric {
    LexerCommander generateLexerCommander(TokenBuilder tokenBuilder, StateRepository stateRepository);
}
