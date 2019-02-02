package com.aleksander.formatter.lexer;

import com.aleksander.formatter.iohundlers.Reader;
import com.aleksander.formatter.iohundlers.exceptions.DefaultIOException;
import com.aleksander.formatter.lexer.commands.LexerCommander;
import com.aleksander.formatter.lexer.commands.tcommands.Command;
import com.aleksander.formatter.lexer.exceptions.LexerException;
import com.aleksander.formatter.lexer.lstate.LexerState;
import com.aleksander.formatter.lexer.lstate.StateTransfer;
import com.aleksander.formatter.utils.Token;
import com.aleksander.formatter.lexer.token.TokenBuilder;

import java.util.ArrayDeque;

public class SimpleLexer implements Lexer {

    private Reader reader;
    private TokenBuilder tokenBuilder;

    private StateTransfer stateTransfer; // = init by fabrics
    private LexerCommander lexerCommander; // = init by fabrics
    private ArrayDeque<Token> bufferTokens;

    public SimpleLexer(Reader reader, TokenBuilder tokenBuilder, StateTransfer stateTransfer, LexerCommander lexerCommander) {
        this.tokenBuilder = tokenBuilder;
        this.reader = reader;
        this.stateTransfer = stateTransfer;
        this.lexerCommander = lexerCommander;
        bufferTokens = new ArrayDeque<>();
    }

    @Override
    public Token nextToken() throws LexerException {
        if(!bufferTokens.isEmpty()) {
            return bufferTokens.poll();
        }
        LexerState lexerState = stateTransfer.startedLexerState();
        Command command = null;
        char symbol = 0;
        try {
            while (reader.ready() && !tokenBuilder.formed()) {
                symbol = reader.read();
                lexerState = stateTransfer.nextState(symbol, lexerState);
                command = lexerCommander.generateCommand(symbol, lexerState);
                command.execute();
            }
        } catch (DefaultIOException e) {
            throw new LexerException("Inner Lexer: " + e.getMessage());
        }
        bufferTokens.addAll(tokenBuilder.getTokens());
        return bufferTokens.poll();
    }

    @Override
    public boolean hasNextToken() throws LexerException {
        try {
            return reader.ready() || !bufferTokens.isEmpty();
        } catch (DefaultIOException e) {
            throw new LexerException(e.getMessage());
        }
    }


}
