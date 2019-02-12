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

    public enum CommandIdentificator {
        EMPTY_COMMAND, FORMED_TOKEN_COMMAND, PREPARATION_COMMAND, FLUSH_COMMAND, APPEND_COMMAND, END_COMMAND;
    }

    private Map<CommandIdentificator, Command> commands;
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
        CompositeCommand endCommand = new CompositeCommand();
        endCommand.add(appendElementCommand);
        endCommand.add(flushCommand);


        updatebleCommands.add(formedTokenCommand);
        updatebleCommands.add(appendElementCommand);

        commands.put(CommandIdentificator.EMPTY_COMMAND, new EmptyCommand());
        commands.put(CommandIdentificator.FORMED_TOKEN_COMMAND, formedTokenCommand);
        commands.put(CommandIdentificator.PREPARATION_COMMAND, preparationCommand);
        commands.put(CommandIdentificator.FLUSH_COMMAND, flushCommand);
        commands.put(CommandIdentificator.APPEND_COMMAND, appendElementCommand);
        commands.put(CommandIdentificator.END_COMMAND, endCommand);

        repository.put(stateRepository.getStateByIdentificator(StateRepository.StateIdentificator.DEFAULT_STATE), commands.get(CommandIdentificator.EMPTY_COMMAND));
        repository.put(stateRepository.getStateByIdentificator(StateRepository.StateIdentificator.FLUSH_STATE), commands.get(CommandIdentificator.FLUSH_COMMAND));
        repository.put(stateRepository.getStateByIdentificator(StateRepository.StateIdentificator.PREPARATION_STATE), commands.get(CommandIdentificator.PREPARATION_COMMAND));
        repository.put(stateRepository.getStateByIdentificator(StateRepository.StateIdentificator.READING_STATE), commands.get(CommandIdentificator.APPEND_COMMAND));
        repository.put(stateRepository.getStateByIdentificator(StateRepository.StateIdentificator.STRING_LITERAL), commands.get(CommandIdentificator.APPEND_COMMAND));
        repository.put(stateRepository.getStateByIdentificator(StateRepository.StateIdentificator.STRING_LITERAL), commands.get(CommandIdentificator.APPEND_COMMAND));
        repository.put(stateRepository.getStateByIdentificator(StateRepository.StateIdentificator.CHAR_LITERAL), commands.get(CommandIdentificator.APPEND_COMMAND));
        repository.put(stateRepository.getStateByIdentificator(StateRepository.StateIdentificator.COMMENT_PRE), commands.get(CommandIdentificator.APPEND_COMMAND));
        repository.put(stateRepository.getStateByIdentificator(StateRepository.StateIdentificator.COMMENT_LINE), commands.get(CommandIdentificator.APPEND_COMMAND));
        repository.put(stateRepository.getStateByIdentificator(StateRepository.StateIdentificator.END_COMMENT), commands.get(CommandIdentificator.FLUSH_COMMAND));
        repository.put(stateRepository.getStateByIdentificator(StateRepository.StateIdentificator.END_STRING_LITERAL), commands.get(CommandIdentificator.END_COMMAND));
        repository.put(stateRepository.getStateByIdentificator(StateRepository.StateIdentificator.END_CHAR_LITERAL), commands.get(CommandIdentificator.END_COMMAND));
    }

    public Command getCommand(String string, LexerState lexerState) {
        updatebleCommands.forEach(upd -> upd.update(string));
        return repository.getOrDefault(lexerState, commands.get(CommandIdentificator.EMPTY_COMMAND));
    }

}
