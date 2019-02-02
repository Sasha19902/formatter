package com.aleksander.formatter.formatterhundlers.commands.tcommands;

import com.aleksander.formatter.utils.Token;

import java.util.Deque;

public class WriteTokenCommand implements Command, UpdatableCommand<Token> {

    private static final int FIRST_OPTION = 0;
    private Deque<Token> tokenQueue;
    private Token token;

    public WriteTokenCommand(Deque<Token> tokenQueue) {
        this.tokenQueue = tokenQueue;
    }

    @Override
    public void execute() {
        tokenQueue.addLast(token);
    }

    @Override
    public void update(Token... options) {
        this.token = options[FIRST_OPTION];
    }
}
