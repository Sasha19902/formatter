package com.aleksander.utils;

import java.io.*;
import java.util.*;

public class SpecialKeys {

    private static final String KEY_SPLIT = "/-/";
    private static final String KEY_NEXT_LINE = "endl";
    private static final String NEXT_LINE = "\n";
    private static final String KEY_WORD_PATH = "KeyWordsFile";
    private static final String KEY_SYMBOL_PATH = "KeySymbolsFile";

    /*KEY->INFO*/
    private Map<String, String> keyWords;
    /*KEY->INFO*/
    private Map<String, String> keySymbols;


    public SpecialKeys() throws IOException {
        keyWords = new HashMap<>();
        keySymbols = new HashMap<>();

        initialKeySymbols();
        initialKeyWords();
    }

    public void initialKeyWords() throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(KEY_WORD_PATH)), "UTF-8"))) {
            int word = 0;
            int lexem = 1;
            String[] parsed = null;
            while (reader.ready()) {
                parsed = reader.readLine().split(KEY_SPLIT);
                keyWords.put(parsed[word], parsed[lexem]);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public void initialKeySymbols() throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(KEY_SYMBOL_PATH)), "UTF-8"))) {
            int word = 0;
            int lexem = 1;
            String[] parsed = null;
            while (reader.ready()) {
                parsed = reader.readLine().split(KEY_SPLIT);
                if (parsed[0].equals(KEY_NEXT_LINE)) {
                    parsed[0] = NEXT_LINE;
                }
                keyWords.put(parsed[word], parsed[lexem]);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public Map<String, String> getKeyWords() {
        return keyWords;
    }

    public Map<String, String> getKeySymbols() {
        return keySymbols;
    }

    public boolean isKey(String string) {
        return keyWords.containsKey(string) || keySymbols.containsKey(string);
    }

    public String getKey(String string) {
        String keyWord = keyWords.get(string);
        String keySymbol = keySymbols.get(string);

        return keyWord == null ? keySymbol : keyWord;
    }
}
