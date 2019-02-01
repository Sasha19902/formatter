package com.aleksander;

import com.aleksander.formatter.Formatter;
import com.aleksander.formatter.SimpleFormatter;
import com.aleksander.formatter.exceptions.FormatterException;
import com.aleksander.iohundlers.exceptions.DefaultIOException;
import com.aleksander.iohundlers.iodefault.DefaultReader;
import com.aleksander.iohundlers.iodefault.DefaultWriter;
import com.aleksander.lexer.Lexer;
import com.aleksander.lexer.LexerFabric;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try(DefaultReader reader = new DefaultReader(scanner.nextLine());
            DefaultWriter defaultWriter = new DefaultWriter(scanner.nextLine())) {

            Lexer lexer = new LexerFabric().generateLexer(reader);
            Formatter formatter = new SimpleFormatter();
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
