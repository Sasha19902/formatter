package com.aleksander.lexer.token;

import com.aleksander.utils.SpecialKeys;

import java.util.HashMap;
import java.util.Map;

public class TokenRepository {

    private Map<String, Token> tokenMap;

    public TokenRepository(SpecialKeys specialKeys) {
        tokenMap = new HashMap<>();
        initializing(specialKeys);
    }

    public void initializing(SpecialKeys specialKeys) {
        Map<String, String> keyWords = specialKeys.getKeyWords();
        Map<String, String> keySymbols = specialKeys.getKeySymbols();

        for(Map.Entry<String, String> entry : keyWords.entrySet()) {
            tokenMap.put(entry.getKey(), new LexerToken(entry.getKey(), entry.getValue()));
        }

        for(Map.Entry<String, String> entry : keySymbols.entrySet()) {
            tokenMap.put(entry.getKey(), new LexerToken(entry.getKey(), entry.getValue()));
        }
    }

    public Token formToken(String content) {
        return tokenMap.getOrDefault(content, new LexerToken(content, Token.TT_DEFAULT));
    }
}
