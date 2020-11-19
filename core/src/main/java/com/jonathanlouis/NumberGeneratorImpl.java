package com.jonathanlouis;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Getter
public class NumberGeneratorImpl implements NumberGenerator {

    // -- fields --
    @Getter(AccessLevel.NONE)
    private final Random random = new Random();

    private final int maxNumber;
    private final int smallestNumber;

    // -- constructors --
    @Autowired
    public NumberGeneratorImpl(@MaxNumber int maxNumber, @SmallestNumber int smallestNumber) {
        this.maxNumber = maxNumber;
        this.smallestNumber = smallestNumber;
    }

    // -- public methods --
    @Override
    public int next() {
        return random.nextInt(maxNumber - smallestNumber) + smallestNumber;
    }
}
