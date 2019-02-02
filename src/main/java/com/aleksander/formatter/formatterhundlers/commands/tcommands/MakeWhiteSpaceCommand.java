package com.aleksander.formatter.formatterhundlers.commands.tcommands;

import com.aleksander.formatter.formatterhundlers.FormatterToken;
import com.aleksander.formatter.utils.Token;

import java.util.Deque;

public class MakeWhiteSpaceCommand implements Command, UpdatableCommand<Token> {

    private static final int FIRST_OPTION = 0;
    private static final String WHITE_SPACE = " ";
    private static final String DESCRIPTION = "WHITE_SPACE";
    private static final String DISABLER_SEMICOLON = ";";
    private static final String DISABLER_RIGHT_BRACKET = "}";
    private Deque<Token> tokens;
    private FormatterToken formatterToken;
    private boolean whiteSpace;

    public MakeWhiteSpaceCommand(Deque<Token> tokens) {
        this.tokens = tokens;
        formatterToken = new FormatterToken(WHITE_SPACE, DESCRIPTION);
    }

    @Override
    public void execute() {
        whiteSpace = true;
    }

    @Override
    public void update(Token... options) {
        if(options[FIRST_OPTION].getContent().equals(DISABLER_SEMICOLON) || options[FIRST_OPTION].getContent().equals(DISABLER_RIGHT_BRACKET)) {
            whiteSpace = false;
        }

        if(whiteSpace) {
            tokens.addLast(formatterToken);
            whiteSpace = false;
        }
    }
}
