package com.aleksander.formatter.lexer.commands.tcommands;

public interface UpdatebleCommand<U> {
    void update(U ... options);
}
