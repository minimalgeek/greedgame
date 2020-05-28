package com.greed.game.controller;

import com.greed.game.implementation.DiceRoller;
import com.greed.game.implementation.GreedGame;
import com.greed.game.implementation.ScoreEvaluator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(GreedGameController.class)
public class GreedGameControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GreedGame service;


    @MockBean
    private DiceRoller diceRoller;


    @MockBean
    private ScoreEvaluator scoreEvaluator;

    @Test
    public void testSimpleGameReturnsWithDoubleZeroScore() throws Exception {
        when(service.getScores()).thenReturn(new int[]{0,0});
        mvc.perform(get("/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //200
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0]", is(0)))
                .andExpect(jsonPath("$[1]", is(0)));
    }

    @Test
    public void testGameNextTurn() throws Exception {
        when(service.getScores()).thenReturn(new int[]{150,0});
        mvc.perform(get("/nextTurn").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //200
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0]", is(150)))
                .andExpect(jsonPath("$[1]", is(0)));
    }

}
