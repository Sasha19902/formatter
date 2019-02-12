package com.aleksander.formatter.lexer.token;

import com.aleksander.formatter.utils.SpecialKeys;
import com.aleksander.formatter.utils.Token;

import java.util.HashMap;
import java.util.Map;

public class TokenRepository {

    private Map<String, Token> tokenMap;
    private Map<String, Token> tokenMapByInfo;

    public TokenRepository(SpecialKeys specialKeys) {
        tokenMap = new HashMap<>();
        tokenMapByInfo = new HashMap<>();
        initializing(specialKeys);
    }

    private void initializing(SpecialKeys specialKeys) {
        Map<String, String> keyWords = specialKeys.getKeyWords();
        Map<String, String> keySymbols = specialKeys.getKeySymbols();

        for(Map.Entry<String, String> entry : keyWords.entrySet()) {
            tokenMap.put(entry.getKey(), new LexerToken(entry.getKey(), entry.getValue()));
        }

        for(Map.Entry<String, String> entry : keySymbols.entrySet()) {
            tokenMap.put(entry.getKey(), new LexerToken(entry.getKey(), entry.getValue()));
        }
        for(Token token : tokenMap.values()) {
            tokenMapByInfo.put(token.getType(), token);
        }
    }



    public Token formToken(String content) {
        //commentToken
        if(content.startsWith("//")) {
            return new LexerToken(content, Token.TT_COMMENT);
        }

        return tokenMap.getOrDefault(content, new LexerToken(content, Token.TT_DEFAULT));
    }

    public Token formTokenByInfo(String info) {
        return tokenMapByInfo.get(info);
    }

}
