package com.greed.game.implementation;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class ScoreEvaluator {
    public int evaluate(int[] arrayOfDiceRollResult) {

        Map<Integer, Integer> counter = new HashMap<>();
        Arrays.stream(arrayOfDiceRollResult)
                .forEach(value -> counter.merge(value, 1, Integer::sum));

        if (counter.entrySet().stream().allMatch(integerIntegerEntry -> integerIntegerEntry.getValue() == 2))
            return 800;
        if (counter.entrySet().size() == 6 &&
            counter.entrySet().stream().allMatch(integerIntegerEntry -> integerIntegerEntry.getValue() == 1))
            return 1200;

        return counter.entrySet().stream()
                .mapToInt(keyValuePair -> {
                    Integer key = keyValuePair.getKey();
                    Integer amount = keyValuePair.getValue();

                    int multiplier = amount >= 3 ? 100 * (int) Math.pow(2, amount-3) : 1;

                    if (amount >= 3) {
                        if (key == 1) {
                            return multiplier * 10;
                        }
                        return multiplier * key;
                    } else {
                        if (key == 1) {
                            return 100;
                        } else if(key == 5) {
                            return 50;
                        }
                    }
                    return 0;
                }).sum();
    }
}
