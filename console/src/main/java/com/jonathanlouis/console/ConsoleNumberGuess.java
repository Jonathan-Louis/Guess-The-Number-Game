package com.jonathanlouis.console;

import com.jonathanlouis.Game;
import com.jonathanlouis.MessageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Slf4j
public class ConsoleNumberGuess{

    //-- constants --

    //-- fields --
    private final Game game;
    private final MessageGenerator messageGenerator;

    // -- constructors
    @Autowired
    public ConsoleNumberGuess(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }

    //-- events --
    @EventListener(ContextRefreshedEvent.class)
    public void start() {
        log.info("start() --> Container ready for use.");

        Scanner scanner = new Scanner(System.in);

        //loop until player breaks
        while(true){
            System.out.println(messageGenerator.getMainMessage());
            System.out.println(messageGenerator.getResultMessage());

            if(scanner.hasNextInt()) {

                int guess = scanner.nextInt();
                scanner.nextLine();
                game.setGuess(guess);
                game.check();
            } else {
                System.out.println("Invalid input, please enter an integer.");
                scanner.nextLine();
                continue;
            }

            if(game.isGameWon() || game.isGameLost()){
                System.out.println(messageGenerator.getResultMessage());
                System.out.println("Play again, Y/N?");
                String playAgainString = scanner.nextLine().trim().toLowerCase();
                if(!playAgainString.equalsIgnoreCase("y")){
                    break;
                }

                game.reset();
            }
        }
    }
}
