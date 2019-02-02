package com.aleksander.formatter.formatterhundlers.fabrics;

import com.aleksander.formatter.formatterhundlers.commands.FormatterCommandRepository;
import com.aleksander.formatter.formatterhundlers.commands.FormatterCommander;
import com.aleksander.formatter.formatterhundlers.commands.SimpleFormatterCommander;
import com.aleksander.formatter.formatterhundlers.fstate.StateRepository;
import com.aleksander.formatter.utils.Token;

import java.util.Deque;

public class FormatterCommanderFabricImpl implements FormatterCommanderFabric {
    @Override
    public FormatterCommander generateFormatterCommander(Deque<Token> buffer, StateRepository stateRepository) {
        FormatterCommandRepository formatterCommandRepository = new FormatterCommandRepository(stateRepository, buffer);
        return new SimpleFormatterCommander(formatterCommandRepository);
    }
}
