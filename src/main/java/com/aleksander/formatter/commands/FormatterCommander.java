package com.aleksander.formatter.commands;

import com.aleksander.formatter.commands.tcommands.Command;
import com.aleksander.formatter.fstate.FormatterState;
import com.aleksander.lexer.token.Token;

public interface FormatterCommander {
    Command generateCommand(Token token, FormatterState formatterState);
}
