package com.aleksander.formatter;

import com.aleksander.formatter.exceptions.FormatterException;
import com.aleksander.iohundlers.Writer;
import com.aleksander.lexer.Lexer;

public interface Formatter {
    void format(Lexer lexer, Writer writer) throws FormatterException;
}
