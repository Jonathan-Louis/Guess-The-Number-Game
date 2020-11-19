package com.jonathanlouis.config;

import com.jonathanlouis.GuessCount;
import com.jonathanlouis.MaxNumber;
import com.jonathanlouis.SmallestNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/game.properties")
public class GameConfig {

    // -- fields --
    @Value("${game.maxNumber:20}")
    private int maxNumber;

    @Value("${game.smallestNumber:1}")
    private int smallestNumber;

    @Value("${game.guessCount:5}")
    private int guessCount;

    // -- beans --
    @Bean
    @MaxNumber
    public int getMaxNumber(){
        return maxNumber;
    }

    @Bean
    @GuessCount
    public int getGuessCount(){
        return guessCount;
    }

    @Bean
    @SmallestNumber
    public int getSmallestNumber(){
        return smallestNumber;
    }
}
