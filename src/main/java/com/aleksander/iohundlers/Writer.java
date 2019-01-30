package com.aleksander.iohundlers;

import com.aleksander.iohundlers.exceptions.DefaultIOException;

public interface Writer {
    void write(char smb) throws DefaultIOException;
    void write(String string) throws DefaultIOException;
}
