package com.aleksander.formatter.lexer.commands.tcommands;

import com.aleksander.formatter.lexer.token.TokenBuilder;

public class FormedTokenCommand implements Command, UpdatebleCommand<String> {

    private static final int FIRST_OPTION = 0;
    private TokenBuilder tokenBuilder;
    private String element;

    public FormedTokenCommand(TokenBuilder tokenBuilder) {
        this.tokenBuilder = tokenBuilder;
    }

    @Override
    public void execute() {
        tokenBuilder.formateBufferToken(element);
    }

    @Override
    public void update(String... options) {
        element = options[FIRST_OPTION];
    }
}
