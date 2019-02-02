package com.aleksander.formatter.lexer.commands;

import com.aleksander.formatter.lexer.commands.tcommands.Command;
import com.aleksander.formatter.lexer.lstate.LexerState;

public interface LexerCommander {
    Command generateCommand(char smb, LexerState lexerState);
}
