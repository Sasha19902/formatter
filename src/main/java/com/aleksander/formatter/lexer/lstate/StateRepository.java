package com.aleksander.formatter.lexer.lstate;

import com.aleksander.formatter.lexer.Lexer;
import com.aleksander.formatter.utils.Pair;

import java.util.HashMap;
import java.util.Map;

public class StateRepository {

    public enum StateIdentificator {

        DEFAULT_STATE("DEFAULT_STATE"), READING_STATE("READING_STATE"), FLUSH_STATE("FLUSH_STATE"),
        PREPARATION_STATE("PREPARATION_STATE"), STRING_LITERAL("STRING_LITERAL"), END_STRING_LITERAL("END_STRING_LITERAL"), CHAR_LITERAL("CHAR_LITERAL"),
        END_CHAR_LITERAL("END_CHAR_LITERAL"), COMMENT_PRE("COMMENT_PRE"), COMMENT_LINE("COMMENT"), END_COMMENT("END_COMMENT");
        StateIdentificator(String name) {
            this.name = name;
        }

        private String name;

        public String getName() {
            return name;
        }
    }

    private Map<StateIdentificator, LexerState> states;
    private Map<Pair<LexerState, String>, LexerState> lexerStateMap;
    private Map<String, String> keySymbols;
    private Map<LexerState, LexerState> independentState;

    public StateRepository(Map<String, String> keySymbols) {
        this.keySymbols = keySymbols;
        this.lexerStateMap = new HashMap<>();
        states = new HashMap<>();
        independentState = new HashMap<>();

        states.put(StateIdentificator.DEFAULT_STATE, new LState(StateIdentificator.DEFAULT_STATE.name));
        states.put(StateIdentificator.READING_STATE, new LState(StateIdentificator.READING_STATE.name));
        states.put(StateIdentificator.FLUSH_STATE, new LState(StateIdentificator.FLUSH_STATE.name));
        states.put(StateIdentificator.PREPARATION_STATE, new LState(StateIdentificator.PREPARATION_STATE.name));
        states.put(StateIdentificator.STRING_LITERAL, new LState(StateIdentificator.STRING_LITERAL.name));
        states.put(StateIdentificator.END_STRING_LITERAL, new LState(StateIdentificator.END_STRING_LITERAL.name));
        states.put(StateIdentificator.CHAR_LITERAL, new LState(StateIdentificator.CHAR_LITERAL.name));
        states.put(StateIdentificator.END_CHAR_LITERAL, new LState(StateIdentificator.END_CHAR_LITERAL.name));
        states.put(StateIdentificator.COMMENT_LINE, new LState(StateIdentificator.COMMENT_LINE.name));
        states.put(StateIdentificator.COMMENT_PRE, new LState(StateIdentificator.COMMENT_PRE.name));
        states.put(StateIdentificator.END_COMMENT, new LState(StateIdentificator.END_COMMENT.name));

        for (Map.Entry<String, String> element : keySymbols.entrySet()) {
            lexerStateMap.put(new Pair<>(states.get(StateIdentificator.DEFAULT_STATE), element.getKey()), states.get(StateIdentificator.PREPARATION_STATE));
            lexerStateMap.put(new Pair<>(states.get(StateIdentificator.READING_STATE), element.getKey()), states.get(StateIdentificator.PREPARATION_STATE));
        }

        //String literal forming
        independentState.put(states.get(StateIdentificator.STRING_LITERAL), states.get(StateIdentificator.STRING_LITERAL));
        lexerStateMap.put(new Pair<>(states.get(StateIdentificator.DEFAULT_STATE), "\""), states.get(StateIdentificator.STRING_LITERAL));
        lexerStateMap.put(new Pair<>(states.get(StateIdentificator.READING_STATE), "\""), states.get(StateIdentificator.STRING_LITERAL));
        lexerStateMap.put(new Pair<>(states.get(StateIdentificator.STRING_LITERAL), "\""), states.get(StateIdentificator.END_STRING_LITERAL));

        //Char literal forming
        independentState.put(states.get(StateIdentificator.CHAR_LITERAL), states.get(StateIdentificator.CHAR_LITERAL));
        lexerStateMap.put(new Pair<>(states.get(StateIdentificator.DEFAULT_STATE), "\'"), states.get(StateIdentificator.CHAR_LITERAL));
        lexerStateMap.put(new Pair<>(states.get(StateIdentificator.READING_STATE), "\'"), states.get(StateIdentificator.CHAR_LITERAL));
        lexerStateMap.put(new Pair<>(states.get(StateIdentificator.CHAR_LITERAL), "\'"), states.get(StateIdentificator.END_CHAR_LITERAL));

        //Comment forming
        independentState.put(states.get(StateIdentificator.COMMENT_LINE), states.get(StateIdentificator.COMMENT_LINE));
        lexerStateMap.put(new Pair<>(states.get(StateIdentificator.DEFAULT_STATE), "/"), states.get(StateIdentificator.COMMENT_PRE));
        lexerStateMap.put(new Pair<>(states.get(StateIdentificator.PREPARATION_STATE), "/"), states.get(StateIdentificator.COMMENT_PRE));
        lexerStateMap.put(new Pair<>(states.get(StateIdentificator.FLUSH_STATE), "/"), states.get(StateIdentificator.COMMENT_PRE));
        lexerStateMap.put(new Pair<>(states.get(StateIdentificator.COMMENT_PRE), "/"), states.get(StateIdentificator.COMMENT_LINE));
        lexerStateMap.put(new Pair<>(states.get(StateIdentificator.COMMENT_LINE), "\n"), states.get(StateIdentificator.END_COMMENT));
    }

    public LexerState getState(LexerState prevState, String element) {
        LexerState depState = lexerStateMap.getOrDefault(new Pair<>(prevState, element), new LState(StateIdentificator.READING_STATE.name));
        LexerState indepState = independentState.getOrDefault(prevState, new LState(StateIdentificator.READING_STATE.name));

        if(depState.equals(states.get(StateIdentificator.READING_STATE))) {
            return indepState;
        } else {
            return depState;
        }
    }

    public LexerState getStateByIdentificator(StateIdentificator stateIdentificator) {
        return states.get(stateIdentificator);
    }
}
