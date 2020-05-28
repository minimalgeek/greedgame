package com.greed.game.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class DiceRollerTest {

    private DiceRoller diceRoller;

    @BeforeEach
    public void init() {
        diceRoller = new DiceRoller();
    }

    @RepeatedTest(200)
    public void rollOneDieIsBetweenOneAndSix() {
        assertThat(diceRoller.rollOne()).isBetween(1,6);
    }

    @RepeatedTest(200)
    public void rollFourDiceAndAllBetweenOneAndSix() {
        assertThat(diceRoller.roll(4))
                .hasSize(4)
                .matches(ints -> Arrays.stream(ints).allMatch(value -> value > 0 && value < 7));
    }

    @RepeatedTest(200)
    public void rollSixDiceAndAllBetweenOneAndSix() {
        assertThat(diceRoller.roll(6))
                .hasSize(6)
                .matches(ints -> Arrays.stream(ints).allMatch(value -> value > 0 && value < 7));
    }

}
