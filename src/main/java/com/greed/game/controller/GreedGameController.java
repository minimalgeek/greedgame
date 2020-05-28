package com.greed.game.controller;

import com.greed.game.implementation.GreedGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreedGameController {

    @Autowired
    private GreedGame greedGame;

    @GetMapping("/")
    public int[] root() {
        return greedGame.getScores();
    }

    @GetMapping("/nextTurn")
    public int[] nextTurn() {
        greedGame.nextTurn();
        return greedGame.getScores();
    }


}
