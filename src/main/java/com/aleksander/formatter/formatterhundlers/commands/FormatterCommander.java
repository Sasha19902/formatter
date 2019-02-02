package com.aleksander.formatter.formatterhundlers.commands;

import com.aleksander.formatter.formatterhundlers.commands.tcommands.Command;
import com.aleksander.formatter.formatterhundlers.fstate.FormatterState;
import com.aleksander.formatter.utils.Token;

public interface FormatterCommander {
    Command generateCommand(Token token, FormatterState formatterState);
}
