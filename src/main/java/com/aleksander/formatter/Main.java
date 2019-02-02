package com.aleksander.formatter;

import com.aleksander.formatter.formatterhundlers.Formatter;
import com.aleksander.formatter.formatterhundlers.FormatterFabric;
import com.aleksander.formatter.formatterhundlers.exceptions.FormatterException;
import com.aleksander.formatter.iohundlers.exceptions.DefaultIOException;
import com.aleksander.formatter.iohundlers.iodefault.DefaultReader;
import com.aleksander.formatter.iohundlers.iodefault.DefaultWriter;
import com.aleksander.formatter.lexer.Lexer;
import com.aleksander.formatter.lexer.LexerFabric;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        try(DefaultReader reader = new DefaultReader(scanner.nextLine());
            DefaultWriter defaultWriter = new DefaultWriter(scanner.nextLine())) {

            Lexer lexer = new LexerFabric().generateLexer(reader);
            Formatter formatter = new FormatterFabric().getFormatter();
            try {
                formatter.format(lexer, defaultWriter);
            } catch (FormatterException e) {
                e.printStackTrace();
            }
        } catch (DefaultIOException e) {
            e.printStackTrace();
        }
    }
}
