package com.aleksander.formatter.lexer.commands;

import com.aleksander.formatter.lexer.commands.tcommands.*;
import com.aleksander.formatter.lexer.lstate.LexerState;
import com.aleksander.formatter.lexer.lstate.StateRepository;
import com.aleksander.formatter.lexer.token.TokenBuilder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LexerCommandRepository {

    private static final String EMPTY_COMMAND = "EMPTY_COMMAND";
    private static final String FORMED_TOKEN_COMMAND = "FT_COMMAND";
    private static final String PREPARATION_COMMAND = "PREPARATION_COMMAND";
    private static final String FLUSH_COMMAND = "FLUSH_COMMAND";
    private static final String APPEND_COMMAND = "APPEND_COMMAND";

    private Map<String, Command> commands;
    private Map<LexerState, Command> repository;
    private Set<UpdatebleCommand<String>> updatebleCommands;

    public LexerCommandRepository(TokenBuilder tokenBuilder, StateRepository stateRepository) {
        repository = new HashMap<>();
        commands = new HashMap<>();
        updatebleCommands = new HashSet<>();

        FormedTokenCommand formedTokenCommand = new FormedTokenCommand(tokenBuilder);
        FlushCommand flushCommand = new FlushCommand(tokenBuilder);
        CompositeCommand preparationCommand = new CompositeCommand();
        preparationCommand.add(formedTokenCommand);
        preparationCommand.add(flushCommand);
        AppendElementCommand appendElementCommand = new AppendElementCommand(tokenBuilder);


        updatebleCommands.add(formedTokenCommand);
        updatebleCommands.add(appendElementCommand);

        commands.put(EMPTY_COMMAND, new EmptyCommand());
        commands.put(FORMED_TOKEN_COMMAND, formedTokenCommand);
        commands.put(PREPARATION_COMMAND, preparationCommand);
        commands.put(FLUSH_COMMAND, flushCommand);
        commands.put(APPEND_COMMAND, appendElementCommand);

        repository.put(stateRepository.getDefaultState(), commands.get(EMPTY_COMMAND));
        repository.put(stateRepository.getFlushState(), commands.get(FLUSH_COMMAND));
        repository.put(stateRepository.getPreparationState(), commands.get(PREPARATION_COMMAND));
        repository.put(stateRepository.getReadingState(), commands.get(APPEND_COMMAND));
    }

    public Command getCommand(String string, LexerState lexerState) {
        updatebleCommands.forEach(upd -> upd.update(string));
        return repository.getOrDefault(lexerState, commands.get(EMPTY_COMMAND));
    }

}
