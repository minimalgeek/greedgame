package com.greed.game.implementation;

import com.greed.game.implementation.GreedGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalMatchers.aryEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GreedGameTest {

    private GreedGame game;
    private DiceRoller diceRoller;
    private ScoreEvaluator scoreEvaluator;

    @BeforeEach
    public void init() {
        diceRoller = mock(DiceRoller.class);
        scoreEvaluator = mock(ScoreEvaluator.class);
        game = new GreedGame(diceRoller, scoreEvaluator);
    }

    @Test
    public void smokeTest() {
        assertThat(game).isNotNull();
    }

    @Test
    public void gameShouldContainTwoScores() {
        assertThat(game.getScores()).hasSize(2);
    }

    @Test
    public void gameShouldContainTwoZeroScores() {
        assertThat(game.getScores()).contains(0, 0);
    }

    @Test
    public void gameProgressesOnNextTurn() {
        when(diceRoller.roll(6)).thenReturn(new int[]{1,2,3,4,5,2});
        when(scoreEvaluator.evaluate(aryEq(new int[]{1,2,3,4,5,2}))).thenReturn(150);
        game.nextTurn();

        assertThat(game.getScores()).hasSize(2)
                .containsExactly(150, 0);
    }

    @Test
    public void gameProgressesOnTwoTurns() {
        when(diceRoller.roll(6)).thenReturn(new int[]{1,2,3,4,5,2});
        when(scoreEvaluator.evaluate(aryEq(new int[]{1,2,3,4,5,2}))).thenReturn(150);
        game.nextTurn();

        when(diceRoller.roll(6)).thenReturn(new int[]{1,1,1,2,2,5});
        when(scoreEvaluator.evaluate(aryEq(new int[]{1,1,1,2,2,5}))).thenReturn(1050);
        game.nextTurn();

        assertThat(game.getScores()).hasSize(2)
                .containsExactly(150, 1050);
    }

    @Test
    public void gameProgressesOnThreeTurns() {
        when(diceRoller.roll(6)).thenReturn(new int[]{1,2,3,4,5,2});
        when(scoreEvaluator.evaluate(aryEq(new int[]{1,2,3,4,5,2}))).thenReturn(150);
        game.nextTurn();

        when(diceRoller.roll(6)).thenReturn(new int[]{1,1,1,2,2,5});
        when(scoreEvaluator.evaluate(aryEq(new int[]{1,1,1,2,2,5}))).thenReturn(1050);
        game.nextTurn();

        when(diceRoller.roll(6)).thenReturn(new int[]{5, 1, 4,4,4,6});
        when(scoreEvaluator.evaluate(aryEq(new int[]{5, 1, 4,4,4,6}))).thenReturn(550);
        game.nextTurn();

        assertThat(game.getScores()).hasSize(2)
                .containsExactly(700, 1050);
    }

}
