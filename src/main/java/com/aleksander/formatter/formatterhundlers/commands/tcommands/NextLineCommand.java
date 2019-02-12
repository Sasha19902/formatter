package com.aleksander.formatter.formatterhundlers.commands.tcommands;

import com.aleksander.formatter.formatterhundlers.FormatterToken;
import com.aleksander.formatter.utils.Token;

import java.util.Deque;

public class NextLineCommand implements Command, UpdatableCommand<Token> {

    private static final int FIRST_OPTION = 0;
    private static final int MAX_ENDL = 3;
    private static final String NEXT_LINE = "\n";
    private static final String WHITE_SPACE = "WHITE_SPACE";
    private static final String DESCRIPTION = "NEXT_LINE_COMMAND";
    private Deque<Token> tokens;
    private FormatterToken formatterToken;
    private boolean nextLine;
    private int count;


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
        if ((nextLine && !options[FIRST_OPTION].getType().equals(WHITE_SPACE) && !options[FIRST_OPTION].getType().equals(Token.TT_COMMENT)) ||
                (options[FIRST_OPTION].getType().equals(Token.TT_COMMENT) && count != 0)) {

            if (options[FIRST_OPTION].getContent().equals(NEXT_LINE) && count < MAX_ENDL) {
                count++;
                tokens.addLast(formatterToken);
            } else {
                if (count != 0) {
                    count = 0;
                } else {
                    tokens.addLast(formatterToken);
                }
                nextLine = false;
            }
        }
    }

    public boolean isNextLine() {
        return nextLine && count != 0;
    }
}
