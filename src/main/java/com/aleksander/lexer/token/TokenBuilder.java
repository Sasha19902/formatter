package com.aleksander.lexer.token;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TokenBuilder {

    private static final int ZERO_MEMORY = 0;
    private StringBuilder stringBuilder;
    private TokenRepository tokenRepository;
    private boolean form;
    private ArrayDeque<Token> buffer;

    public TokenBuilder(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
        stringBuilder = new StringBuilder();
        buffer = new ArrayDeque<>();
    }

    public void appendElement(char element) {
        stringBuilder.append(element);
    }

    public void appendElement(String element) {
        stringBuilder.append(element);
    }

    public void formateBufferToken(String buff) {
        buffer.addLast(tokenRepository.formToken(buff));
    }

    public List<Token> getTokens() {
        buffer.addFirst(tokenRepository.formToken(stringBuilder.toString()));
        List<Token> result = new ArrayList<>(buffer);
        while (!buffer.isEmpty()) {
            buffer.pop();
        }
        return result;
    }

    public boolean formed() {
        boolean resultV = form;
        form = false;

        return resultV;
    }

    public void clean() {
        stringBuilder.setLength(ZERO_MEMORY);
    }

    public void endAppending() {
        form = true;
    }
}
