package com.aleksander.lexer.commands;

import com.aleksander.lexer.commands.tcommands.Command;
import com.aleksander.lexer.lstate.LexerState;

public class SimpleLexerCommander implements LexerCommander {

    private static final int ZERO_MEMORY = 0;
    private LexerCommandRepository lexerCommandRepository;
    private StringBuilder stringBuilder;

    public SimpleLexerCommander(LexerCommandRepository lexerCommandRepository) {
        this.lexerCommandRepository = lexerCommandRepository;
        stringBuilder = new StringBuilder();
    }

    @Override
    public Command generateCommand(char smb, LexerState lexerState) {
        stringBuilder.append(smb);
        Command command = lexerCommandRepository.getCommand(stringBuilder.toString(), lexerState);
        stringBuilder.setLength(ZERO_MEMORY);
        return command;
    }
}
