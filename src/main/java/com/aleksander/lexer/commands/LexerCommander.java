package com.aleksander.lexer.commands;

import com.aleksander.lexer.commands.tcommands.Command;
import com.aleksander.lexer.lstate.LexerState;

public interface LexerCommander {
    Command generateCommand(char smb, LexerState lexerState);
}
