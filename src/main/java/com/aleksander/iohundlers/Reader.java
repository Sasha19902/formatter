package com.aleksander.iohundlers;


import com.aleksander.iohundlers.exceptions.DefaultIOException;

public interface Reader extends AutoCloseable {
    char read() throws DefaultIOException;
    boolean ready() throws DefaultIOException;
}
