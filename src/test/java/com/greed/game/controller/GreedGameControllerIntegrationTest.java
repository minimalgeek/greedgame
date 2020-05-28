package com.greed.game.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GreedGameControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testGameTwoTurns() throws Exception {
        mockMvc.perform(get("/nextTurn").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //200
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0]", greaterThanOrEqualTo(0)))
                .andExpect(jsonPath("$[1]", is(0)));

        mockMvc.perform(get("/nextTurn").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //200
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0]", greaterThanOrEqualTo(0)))
                .andExpect(jsonPath("$[1]", greaterThanOrEqualTo(0)));
    }
}
