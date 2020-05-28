package com.greed.game.implementation;

import org.springframework.stereotype.Service;

@Service
public class GreedGame {

    private DiceRoller diceRoller;
    private ScoreEvaluator scoreEvaluator;

    private int currentPlayerIndex = 0;
    private int[] scores = new int[2];

    public GreedGame(DiceRoller diceRoller, ScoreEvaluator scoreEvaluator) {
        this.diceRoller = diceRoller;
        this.scoreEvaluator = scoreEvaluator;
    }

    public int[] getScores() {
        return scores;
    }

    public void nextTurn() {
        scores[currentPlayerIndex] += scoreEvaluator.evaluate(diceRoller.roll(6));
        currentPlayerIndex = (currentPlayerIndex + 1) % scores.length;
    }
}
