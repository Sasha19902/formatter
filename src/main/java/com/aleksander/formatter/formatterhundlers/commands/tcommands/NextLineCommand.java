package com.aleksander.formatter.formatterhundlers.commands.tcommands;

import com.aleksander.formatter.formatterhundlers.FormatterToken;
import com.aleksander.formatter.utils.Token;

import java.util.Deque;

public class NextLineCommand implements Command, UpdatableCommand<Token> {

    private static final String NEXT_LINE = "\n";
    private static final String DESCRIPTION = "NEXT_LINE_COMMAND";
    private Deque<Token> tokens;
    private FormatterToken formatterToken;
    private boolean nextLine;

    public NextLineCommand(Deque<Token> tokens) {
        this.tokens = tokens;
        formatterToken = new FormatterToken(NEXT_LINE, DESCRIPTION);
    }

    @Override
    public void execute() {
        nextLine = true;
    }

    @Override
    public void update(Token... options) {
        if(nextLine) {
            tokens.addLast(formatterToken);
            nextLine = false;
        }
    }
}
