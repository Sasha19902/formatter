package com.aleksander.formatter.formatterhundlers.commands;

import com.aleksander.formatter.formatterhundlers.commands.tcommands.*;
import com.aleksander.formatter.formatterhundlers.fstate.FormatterState;
import com.aleksander.formatter.formatterhundlers.fstate.StateRepository;
import com.aleksander.formatter.utils.Token;

import java.util.*;

public class FormatterCommandRepository {

    public enum CommandIdentificator {
        EMPTY_COMMAND, PREPARATION_COMMAND, WRITE_TOKEN_COMMAND, CHANGE_TABULATION_COMMAND, NEXT_LINE_COMMAND,
        MAKE_WHITE_SPACE_COMMAND
    }

    private Map<CommandIdentificator, Command> commands;
    private Map<FormatterState, Command> commandMap;
    private List<UpdatableCommand<Token>> updatableCommands;

    public FormatterCommandRepository(StateRepository stateRepository, Deque<Token> tokens) {
        commands = new HashMap<>();
        commandMap = new HashMap<>();
        updatableCommands = new ArrayList<>();

        EmptyCommand emptyCommand = new EmptyCommand();
        PreparationCommand preparationCommand = new PreparationCommand();
        WriteTokenCommand writeTokenCommand = new WriteTokenCommand(tokens);
        TabulationCommand changeTabulationCommand = new TabulationCommand(tokens);
        NextLineCommand nextLineCommand = new NextLineCommand(tokens);
        MakeWhiteSpaceCommand makeWhiteSpaceCommand = new MakeWhiteSpaceCommand(tokens);

        commands.put(CommandIdentificator.EMPTY_COMMAND, emptyCommand);
        commands.put(CommandIdentificator.PREPARATION_COMMAND, preparationCommand);
        commands.put(CommandIdentificator.WRITE_TOKEN_COMMAND, writeTokenCommand);
        commands.put(CommandIdentificator.CHANGE_TABULATION_COMMAND, changeTabulationCommand);
        commands.put(CommandIdentificator.NEXT_LINE_COMMAND, nextLineCommand);
        commands.put(CommandIdentificator.MAKE_WHITE_SPACE_COMMAND, makeWhiteSpaceCommand);

        updatableCommands.add(writeTokenCommand);
        updatableCommands.add(makeWhiteSpaceCommand);
        updatableCommands.add(nextLineCommand);
        updatableCommands.add(changeTabulationCommand);


        //RIGHT_BRACKET
        CompositeCommand rightBracket = new CompositeCommand();
        rightBracket.add(nextLineCommand);
        rightBracket.add(changeTabulationCommand);
        rightBracket.add(writeTokenCommand);
        commandMap.put(stateRepository.getStateByIdentificator(StateRepository.StateIdentificator.RIGHT_BRACKET), rightBracket);

        //LEFT_BRACKET
        CompositeCommand leftBracket = new CompositeCommand();
        leftBracket.add(writeTokenCommand);
        leftBracket.add(nextLineCommand);
        leftBracket.add(changeTabulationCommand);
        commandMap.put(stateRepository.getStateByIdentificator(StateRepository.StateIdentificator.LEFT_BRACKET), leftBracket);

        //SEMICOLON
        CompositeCommand semicolon = new CompositeCommand();
        semicolon.add(writeTokenCommand);
        semicolon.add(nextLineCommand);
        semicolon.add(changeTabulationCommand);
        commandMap.put(stateRepository.getStateByIdentificator(StateRepository.StateIdentificator.SEMICOLON), semicolon);

        //DEFAULT_TOKEN
        CompositeCommand defaultToken = new CompositeCommand();
        defaultToken.add(writeTokenCommand);
        defaultToken.add(makeWhiteSpaceCommand);
        commandMap.put(stateRepository.getStateByIdentificator(StateRepository.StateIdentificator.DEFAULT_TOKEN), defaultToken);
    }

    public Command getCommand(FormatterState formatterState, Token token) {
        updatableCommands.forEach(upd -> upd.update(token));
        return commandMap.getOrDefault(formatterState, commands.get(CommandIdentificator.EMPTY_COMMAND));
    }
}
