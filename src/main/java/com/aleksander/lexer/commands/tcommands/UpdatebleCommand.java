package com.aleksander.lexer.commands.tcommands;

public interface UpdatebleCommand<U> {
    void update(U ... options);
}
