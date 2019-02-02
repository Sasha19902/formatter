package com.aleksander.formatter.lexer.commands.tcommands;

import com.aleksander.formatter.lexer.token.TokenBuilder;

public class AppendElementCommand implements Command, UpdatebleCommand<String>{

    private static final int FIRST_OPTION = 0;
    private TokenBuilder tokenBuilder;
    private String element;

    public AppendElementCommand(TokenBuilder tokenBuilder) {
        this.tokenBuilder = tokenBuilder;
    }

    @Override
    public void execute() {
        tokenBuilder.appendElement(element);
    }

    @Override
    public void update(String... options) {
        this.element = options[FIRST_OPTION];
    }
}
