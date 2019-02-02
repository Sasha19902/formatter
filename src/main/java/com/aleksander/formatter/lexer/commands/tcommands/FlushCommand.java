package com.aleksander.formatter.lexer.commands.tcommands;

import com.aleksander.formatter.lexer.token.TokenBuilder;

public class FlushCommand implements Command {

    private TokenBuilder tokenBuilder;

    public FlushCommand(TokenBuilder tokenBuilder) {
        this.tokenBuilder = tokenBuilder;
    }

    @Override
    public void execute() {
        tokenBuilder.endAppending();
    }
}
