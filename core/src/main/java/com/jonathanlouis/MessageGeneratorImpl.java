package com.jonathanlouis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;

@Slf4j
@Component
public class MessageGeneratorImpl implements MessageGenerator {

    // -- constants --
    private static final String MAIN_MESSAGE = "game.main.message";
    private static final String WIN_MESSAGE = "game.win";
    private static final String LOSE_MESSAGE = "game.lose";
    private static final String INVALID_MESSAGE = "game.invalid";
    private static final String FIRST_GUESS_MESSAGE = "game.first.guess";
    private static final String HIGHER_MESSAGE = "game.higher";
    private static final String LOWER_MESSAGE = "game.lower";
    private static final String REMAINING_MESSAGE = "game.remaining";

    // -- fields --
    private final Game game;
    private final MessageSource messageSource;

    //-- constructors --
    @Autowired
    public MessageGeneratorImpl(Game game, MessageSource messageSource) {
        this.game = game;
        this.messageSource = messageSource;
    }

    // -- init methods
    @PostConstruct
    public void init(){
        log.debug("game = {}", game);
    }


    // -- public methods --
    @Override
    public String getMainMessage() {
        return getMessage(MAIN_MESSAGE, game.getSmallestNumber(), game.getBiggest());
//        return "Number is between " + game.getSmallestNumber() + " and " + game.getBiggest() + ". Can you guess it?";
    }

    @Override
    public String getResultMessage() {

        if(game.isGameWon()){
            return getMessage(WIN_MESSAGE, game.getNumber());
//            return "You guessed it! The number was " + game.getNumber();
        } else if(game.isGameLost()){
            return getMessage(LOSE_MESSAGE, game.getNumber());
//            return "You lost. The number was " + game.getNumber();
        } else if(!game.isValidNumberRange()){
            return getMessage(INVALID_MESSAGE);
//            return "Invalid number range";
        } else if(game.getRemainingGuesses() == game.getGuessCount()){
            return getMessage(FIRST_GUESS_MESSAGE);
//            return "What is your first guess?";
        } else{
            String direction = getMessage(LOWER_MESSAGE);
            if(game.getGuess() < game.getNumber()){
                direction = getMessage(HIGHER_MESSAGE);
            }

            return getMessage(REMAINING_MESSAGE, direction, game.getRemainingGuesses());

//            return direction + "! You have " + game.getRemainingGuesses() + " guesses left.";
        }

    }

    //--private methods--
    private String getMessage(String code, Object... args){
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
