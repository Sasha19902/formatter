package com.aleksander.formatter.formatterhundlers;

import com.aleksander.formatter.formatterhundlers.commands.FormatterCommander;
import com.aleksander.formatter.formatterhundlers.commands.tcommands.Command;
import com.aleksander.formatter.formatterhundlers.exceptions.FormatterException;
import com.aleksander.formatter.formatterhundlers.fstate.FormatterState;
import com.aleksander.formatter.formatterhundlers.fstate.StateTransfer;
import com.aleksander.formatter.iohundlers.Writer;
import com.aleksander.formatter.iohundlers.exceptions.DefaultIOException;
import com.aleksander.formatter.lexer.Lexer;
import com.aleksander.formatter.utils.Token;
import com.aleksander.formatter.lexer.exceptions.LexerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;

public class SimpleFormatter implements Formatter {

    private Deque<Token> buffer;
    private StateTransfer stateTransfer;
    private FormatterCommander formatterCommander;
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleFormatter.class);

    public SimpleFormatter(StateTransfer stateTransfer, FormatterCommander formatterCommander, Deque<Token> buffer) {
        this.stateTransfer = stateTransfer;
        this.formatterCommander = formatterCommander;
        this.buffer = buffer;
    }

    @Override
    public void format(Lexer lexer, Writer writer) throws FormatterException {
        //comment
        FormatterState formatterState = stateTransfer.startedFormatterState(); // = default formatterhundlers state (start state);
        Command command = null; //base commands init
        int iteration = 0;
        try {
            while (lexer.hasNextToken()) {
                Token token = lexer.nextToken();
                LOGGER.info(String.format("TOKEN_INFO: %s", token.getType()));
                formatterState = stateTransfer.nextState(token, formatterState);
                command = formatterCommander.generateCommand(token, formatterState);
                command.execute();
                iteration++;

                if (iteration % 5 == 0) {
                    flush(writer);
                }
            }
            flush(writer);
        } catch (LexerException | DefaultIOException e) {
            throw new FormatterException("Inside formatterhundlers: " + e.getMessage());
        }
    }

    private void flush(Writer writer) throws DefaultIOException {
        while (!buffer.isEmpty()) {
            writer.write(buffer.poll().getContent());
        }
    }

}
