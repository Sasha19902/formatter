package com.aleksander.formatter.formatterhundlers.fstate;

import com.aleksander.formatter.lexer.token.TokenRepository;
import com.aleksander.formatter.utils.SpecialKeys;
import com.aleksander.formatter.utils.Token;

import java.util.HashMap;
import java.util.Map;

public class StateRepository {

    public enum StateIdentificator {
        DEFAULT_STATE("DEFAULT_STATE"),DEFAULT_TOKEN("DEFAULT_TOKEN"),
        LEFT_BRACKET("LEFT_BRACKET"), RIGHT_BRACKET("RIGHT_BRACKET"),
        SEMICOLON("SEMICOLON"), WHITE_SPACE("WHITE_SPACE"), END_LINE("END_LINE");;
        StateIdentificator(String name) {
            this.name = name;
        }

        private String name;

        public String getName() {
            return name;
        }
    }

    /*
     * Token Type -> FormatterState
     * */
    private Map<Token, FormatterState> formatterStateMap;
    private Map<StateIdentificator, FormatterState> states;


    public StateRepository(SpecialKeys specialKeys, TokenRepository tokenRepository) {
        formatterStateMap = new HashMap<>();
        states = new HashMap<>();

        FState leftBracket = new FState(StateIdentificator.LEFT_BRACKET.name);
        FState rightBracket = new FState(StateIdentificator.RIGHT_BRACKET.name);
        FState semicolon = new FState(StateIdentificator.SEMICOLON.name);
        FState whiteSpace = new FState(StateIdentificator.WHITE_SPACE.name);
        FState nextLine = new FState(StateIdentificator.END_LINE.name);

        states.put(StateIdentificator.LEFT_BRACKET, leftBracket);
        states.put(StateIdentificator.RIGHT_BRACKET, rightBracket);
        states.put(StateIdentificator.SEMICOLON, semicolon);
        states.put(StateIdentificator.WHITE_SPACE, whiteSpace);
        states.put(StateIdentificator.END_LINE, nextLine);

        formatterStateMap.put(tokenRepository.formTokenByInfo(leftBracket.getInfo()), leftBracket);
        formatterStateMap.put(tokenRepository.formTokenByInfo(rightBracket.getInfo()), rightBracket);
        formatterStateMap.put(tokenRepository.formTokenByInfo(semicolon.getInfo()), semicolon);
        formatterStateMap.put(tokenRepository.formTokenByInfo(whiteSpace.getInfo()), whiteSpace);
        formatterStateMap.put(tokenRepository.formTokenByInfo(nextLine.getInfo()), nextLine);
    }

    public FormatterState getFormatterState(FormatterState formatterState, Token token) {
        return formatterStateMap.getOrDefault(token, states.get(StateIdentificator.DEFAULT_TOKEN));
    }

    public FormatterState getStateByIdentificator(StateIdentificator stateIdentificator) {
        return states.get(stateIdentificator);
    }
}
