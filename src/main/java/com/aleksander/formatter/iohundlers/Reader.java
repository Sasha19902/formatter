package com.aleksander.formatter.iohundlers;


import com.aleksander.formatter.iohundlers.exceptions.DefaultIOException;

public interface Reader {
    char read() throws DefaultIOException;
    boolean ready() throws DefaultIOException;
}
