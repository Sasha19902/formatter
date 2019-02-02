package com.aleksander.formatter.formatterhundlers.fabrics;

import com.aleksander.formatter.formatterhundlers.commands.FormatterCommander;
import com.aleksander.formatter.formatterhundlers.fstate.StateRepository;
import com.aleksander.formatter.utils.Token;

import java.util.Deque;

public interface FormatterCommanderFabric {
    FormatterCommander generateFormatterCommander(Deque<Token> buffer, StateRepository stateRepository);
}
