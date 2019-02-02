package com.aleksander.formatter.lexer.fabrics;

import com.aleksander.formatter.lexer.commands.LexerCommander;
import com.aleksander.formatter.lexer.lstate.StateRepository;
import com.aleksander.formatter.lexer.token.TokenBuilder;

public interface LexerCommanderFabric {
    LexerCommander generateLexerCommander(TokenBuilder tokenBuilder, StateRepository stateRepository);
}
