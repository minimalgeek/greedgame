package com.greed.game.implementation;

import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.stream.IntStream;

@Service
public class DiceRoller {

    private Random random = new Random();

    public int rollOne() {
        return 1 + random.nextInt(6);
    }

    public int[] roll(int numberOfRolls) {
        return IntStream.generate(this::rollOne).limit(numberOfRolls).toArray();
    }
}
