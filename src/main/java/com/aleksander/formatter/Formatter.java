package com.aleksander.formatter;

import com.aleksander.iohundlers.Reader;
import com.aleksander.iohundlers.Writer;

public interface Formatter {
    void format(Reader reader, Writer writer);
}
