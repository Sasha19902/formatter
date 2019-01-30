package com.aleksander.lexer;

import com.aleksander.iohundlers.Reader;
import com.aleksander.iohundlers.exceptions.DefaultIOException;
import com.aleksander.lexer.commands.LexerCommander;
import com.aleksander.lexer.commands.tcommands.Command;
import com.aleksander.lexer.exceptions.LexerException;
import com.aleksander.lexer.lstate.LexerState;
import com.aleksander.lexer.lstate.StateTransfer;

public class SimpleLexer implements Lexer {

    private Reader reader;
    private TokenBuilder tokenBuilder;

    private StateTransfer stateTransfer; // = init by fabrics
    private LexerCommander lexerCommander; // = init by fabrics

    public SimpleLexer(Reader reader) {
        this.reader = reader;
    }

    @Override
    public Token nextToken() throws LexerException {
        LexerState lexerState = stateTransfer.startedLexerState();
        Command command = null;
        char symbol = 0;
        try {
            while (reader.ready()) {
                symbol = reader.read();
                lexerState = stateTransfer.nextState(symbol, lexerState);
                command = lexerCommander.generateCommand(symbol, lexerState);
                command.execute();
            }
        } catch (DefaultIOException e) {
            throw new LexerException("Inner Lexer: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean hasNextToken() throws LexerException {
        return false;
    }
}
