package com.greed.game.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreEvaluatorTest {

    private ScoreEvaluator scoreEvaluator;

    @BeforeEach
    public void init(){
        scoreEvaluator = new ScoreEvaluator();
    }

    @ParameterizedTest
    @MethodSource("testCaseProvider")
    void inputToScoreEvaluation(int[] input, int expectedScore) {
        assertThat(scoreEvaluator.evaluate(input)).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> testCaseProvider() {
        return Stream.of(
                Arguments.of(new int[] {1}, 100),
                Arguments.of(new int[] {5}, 50),
                Arguments.of(new int[] {1, 5}, 150),
                Arguments.of(new int[] {1, 1,1}, 1000),
                Arguments.of(new int[] {2,2,2}, 200),
                Arguments.of(new int[] {3,3,3}, 300),
                Arguments.of(new int[] {4,4,4}, 400),
                Arguments.of(new int[] {5,5,5}, 500),
                Arguments.of(new int[] {6,6,6}, 600),
                Arguments.of(new int[] {6,6,6,1}, 700),
                Arguments.of(new int[] {2,5,2,2}, 250),
                Arguments.of(new int[] {1,1,1,1}, 2000),
                Arguments.of(new int[] {2,2,2,2,1}, 500),
                Arguments.of(new int[] {3,3,3,3,3,1}, 1300),
                Arguments.of(new int[] {5,4,4,4,4,4}, 1650),
                Arguments.of(new int[] {1,1,1,1,1,1}, 8000),
                Arguments.of(new int[] {2,2,3,3,4,4}, 800),
                Arguments.of(new int[] {3,3,5,5,1,1}, 800),
                Arguments.of(new int[] {1,3,2,4,5,6}, 1200)
        );
    }

}
