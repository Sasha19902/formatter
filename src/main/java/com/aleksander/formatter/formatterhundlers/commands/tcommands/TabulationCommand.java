package com.aleksander.formatter.formatterhundlers.commands.tcommands;

import com.aleksander.formatter.formatterhundlers.FormatterToken;
import com.aleksander.formatter.utils.Token;

import java.util.Deque;

public class TabulationCommand implements Command, UpdatableCommand<Token> {

    private static final String LEFT_BRACKET = "{";
    private static final String RIGHT_BRACKET = "}";
    private static final int FIRST_OPTION = 0;
    private static final String TABULATION = "\t";
    private static final String DISCRIPTION = "TABULATION";
    private static final String FAIL_TRIGGER_WS = " ";
    private static final String FAIL_TRIGGER_EL = "\n";
    private static final String FAIL_TRIGGER_COM = Token.TT_COMMENT;
    private FormatterToken formatterToken;
    private Deque<Token> tokens;
    private int level;
    private boolean makeTabulation;
    private NextLineCommand nextLineCommand;

    public TabulationCommand(Deque<Token> tokens, NextLineCommand nextLineCommand) {
        this.tokens = tokens;
        this.formatterToken = new FormatterToken(TABULATION, DISCRIPTION);
        this.nextLineCommand = nextLineCommand;
    }

    @Override
    public void execute() {
        makeTabulation = true;
    }

    @Override
    public void update(Token... options) {
        if(options[FIRST_OPTION].getContent().equals(FAIL_TRIGGER_EL) ||
           options[FIRST_OPTION].getContent().equals(FAIL_TRIGGER_WS) ||
           (options[FIRST_OPTION].getType().equals(FAIL_TRIGGER_COM) && nextLineCommand.isNextLine())) {
            //empty move
        } else {

            if (options[FIRST_OPTION].getContent().equals(RIGHT_BRACKET)) {
                level--;
            }

            if (makeTabulation) {
                for (int i = 0; i < level; i++) {
                    tokens.addLast(formatterToken);
                }
                makeTabulation = false;
            }

            if (options[FIRST_OPTION].getContent().equals(LEFT_BRACKET)) {
                level++;
            }
        }
    }
}
