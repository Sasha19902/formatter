package com.aleksander.formatter.iohundlers.iodefault;

import com.aleksander.formatter.iohundlers.Writer;
import com.aleksander.formatter.iohundlers.exceptions.DefaultIOException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DefaultWriter implements Writer, AutoCloseable {

    private BufferedWriter bufferedWriter;

    public DefaultWriter(String path) throws DefaultIOException {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(path));
        } catch (IOException e) {
            throw new DefaultIOException(e.getMessage());
        }
    }

    @Override
    public void write(char smb) throws DefaultIOException {
        try {
            bufferedWriter.write(smb);
        } catch (IOException e) {
            throw new DefaultIOException(e.getMessage());
        }
    }

    @Override
    public void write(String string) throws DefaultIOException {
        try {
            bufferedWriter.write(string);
        } catch (IOException e) {
            throw new DefaultIOException(e.getMessage());
        }
    }

    @Override
    public void close() throws DefaultIOException {
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            throw new DefaultIOException(e.getMessage());
        }
    }
}
