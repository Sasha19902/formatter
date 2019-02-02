package com.aleksander.formatter.formatterhundlers;

import com.aleksander.formatter.formatterhundlers.exceptions.FormatterException;
import com.aleksander.formatter.iohundlers.Writer;
import com.aleksander.formatter.lexer.Lexer;

public interface Formatter {
    void format(Lexer lexer, Writer writer) throws FormatterException;
}
