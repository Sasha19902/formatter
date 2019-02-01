package com.aleksander.lexer.lstate;

import com.aleksander.utils.Pair;

import java.util.HashMap;
import java.util.Map;

public class StateRepository {

    private static final String DEFAULT_STATE = "DEFAULT";
    private static final String READING_STATE = "READING";
    private static final String FLUSH_STATE = "FLUSH";
    private static final String PREPARATION_STATE = "PREPARATION_STATE";


    private static final String REGEX_ANY_SYMBOL = "[A-Za-z0-9_]";

    private Map<String, LexerState> states;
    private Map<Pair<LexerState, String>, LexerState> lexerStateMap;
    private Map<String, String> keySymbols;

    public StateRepository(Map<String, String> keySymbols) {
        this.keySymbols = keySymbols;
        this.lexerStateMap = new HashMap<>();
        states = new HashMap<>();

        states.put(DEFAULT_STATE, new LState(DEFAULT_STATE));
        states.put(READING_STATE, new LState(READING_STATE));
        states.put(FLUSH_STATE, new LState(FLUSH_STATE));
        states.put(PREPARATION_STATE, new LState(PREPARATION_STATE));

        lexerStateMap.put(new Pair<>(states.get(DEFAULT_STATE), REGEX_ANY_SYMBOL), states.get(READING_STATE));
        lexerStateMap.put(new Pair<>(states.get(READING_STATE), REGEX_ANY_SYMBOL), states.get(READING_STATE));

        for (Map.Entry<String, String> element : keySymbols.entrySet()) {
            lexerStateMap.put(new Pair<>(states.get(DEFAULT_STATE), element.getKey()), states.get(FLUSH_STATE));
            lexerStateMap.put(new Pair<>(states.get(READING_STATE), element.getKey()), states.get(PREPARATION_STATE));
        }
    }

    public LexerState getState(LexerState prevState, String element) {
        return lexerStateMap.getOrDefault(new Pair<>(prevState, element), new LState(DEFAULT_STATE));
    }

    public LexerState getDefaultState() {
        return states.get(DEFAULT_STATE);
    }
    public LexerState getReadingState() { return states.get(READING_STATE); }
    public LexerState getFlushState() { return states.get(FLUSH_STATE); }
    public LexerState getPreparationState() { return states.get(PREPARATION_STATE);}
}
