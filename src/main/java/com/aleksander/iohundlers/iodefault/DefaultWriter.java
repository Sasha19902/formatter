package com.aleksander.iohundlers.iodefault;

import com.aleksander.iohundlers.Writer;
import com.aleksander.iohundlers.exceptions.DefaultIOException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DefaultWriter implements Writer {

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
    public void close() throws DefaultIOException {
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            throw new DefaultIOException(e.getMessage());
        }
    }
}
