package com.aleksander.formatter.formatterhundlers.commands.tcommands;

public interface UpdatableCommand<U> {
    void update(U ... options);
}
