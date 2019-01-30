package com.aleksander.iohundlers.iodefault;

import com.aleksander.iohundlers.Reader;
import com.aleksander.iohundlers.exceptions.DefaultIOException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DefaultReader implements Reader {

    private BufferedReader bufferedReader;

    public DefaultReader(String path) throws DefaultIOException {
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            throw new DefaultIOException(e.getMessage());
        }
    }

    @Override
    public char read() throws DefaultIOException {
        try {
            return (char) bufferedReader.read();
        } catch (IOException e) {
            throw new DefaultIOException(e.getMessage());
        }
    }

    @Override
    public boolean ready() throws DefaultIOException {
        try {
            return bufferedReader.ready();
        } catch (IOException e) {
            throw new DefaultIOException(e.getMessage());
        }
    }

    @Override
    public void close() throws DefaultIOException {
        try {
            bufferedReader.close();
        } catch (IOException e) {
            throw new DefaultIOException(e.getMessage());
        }
    }
}
