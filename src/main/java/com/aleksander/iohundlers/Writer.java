package com.aleksander.iohundlers;

import com.aleksander.iohundlers.exceptions.DefaultIOException;

public interface Writer extends AutoCloseable {
    void write(char smb) throws DefaultIOException;
}
