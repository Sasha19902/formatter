package com.aleksander.formatter.formatterhundlers.commands;

import com.aleksander.formatter.formatterhundlers.commands.tcommands.Command;
import com.aleksander.formatter.formatterhundlers.fstate.FormatterState;
import com.aleksander.formatter.utils.Token;

public class SimpleFormatterCommander implements FormatterCommander {

    private FormatterCommandRepository formatterCommandRepository;

    public SimpleFormatterCommander(FormatterCommandRepository formatterCommandRepository) {
        this.formatterCommandRepository = formatterCommandRepository;
    }

    @Override
    public Command generateCommand(Token token, FormatterState formatterState) {
        return formatterCommandRepository.getCommand(formatterState, token);
    }
}
