package com.aleksander.formatter.iohundlers;

import com.aleksander.formatter.iohundlers.exceptions.DefaultIOException;

public interface Writer {
    void write(char smb) throws DefaultIOException;
    void write(String string) throws DefaultIOException;
}
