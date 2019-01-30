package com.aleksander.formatter;

import com.aleksander.formatter.commands.FormatterCommander;
import com.aleksander.formatter.commands.tcommands.Command;
import com.aleksander.formatter.exceptions.FormatterException;
import com.aleksander.formatter.fabrics.FormatterCommanderFabric;
import com.aleksander.formatter.fabrics.FormatterCommanderFabricImpl;
import com.aleksander.formatter.fabrics.StateTransferFabric;
import com.aleksander.formatter.fabrics.StateTransferFabricImpl;
import com.aleksander.formatter.fstate.FormatterState;
import com.aleksander.formatter.fstate.StateTransfer;
import com.aleksander.iohundlers.Writer;
import com.aleksander.iohundlers.exceptions.DefaultIOException;
import com.aleksander.lexer.Lexer;
import com.aleksander.lexer.Token;
import com.aleksander.lexer.exceptions.LexerException;

public class SimpleFormatter implements Formatter {

    @Override
    public void format(Lexer lexer, Writer writer) throws FormatterException {
        //fabrics
        StateTransferFabric stateTransferFabric = new StateTransferFabricImpl();
        FormatterCommanderFabric formatterCommanderFabric = new FormatterCommanderFabricImpl();

        StateTransfer stateTransfer = stateTransferFabric.generateStateTransfer(); // = generateByFabric
        FormatterState formatterState = stateTransfer.startedFormatterState(); // = default formatter state (start state);
        FormatterCommander formatterCommander = formatterCommanderFabric.generateFormatterCommander();
        Command command = null; //base command init

        try {
            while (lexer.hasNextToken()) {
                Token token = lexer.nextToken();
                formatterState = stateTransfer.nextState(token, formatterState);
                command = formatterCommander.generateCommand(token, formatterState);
                command.execute();
            }
        } catch (LexerException e) {
            throw new FormatterException("Inside formatter: " + e.getMessage());
        }
    }

}
