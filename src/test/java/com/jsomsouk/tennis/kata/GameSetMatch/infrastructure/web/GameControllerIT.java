package com.jsomsouk.tennis.kata.GameSetMatch.infrastructure.web;

import com.jayway.jsonpath.JsonPath;
import com.jsomsouk.tennis.kata.GameSetMatch.application.GameService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Infrastructure - Controller integration tests")
class GameControllerIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private GameService gameService;

    @Test
    @DisplayName("POST /api/v1/games/tennis should create a new game and return its ID and initial score")
    void shouldCompleteFullGameFlow() throws Exception {
        // 1) Create a game
        final var result = mockMvc.perform(post("/api/v1/games/tennis"))
                .andExpect(status().isCreated())
                .andReturn();

        final var gameId = JsonPath.read(result.getResponse().getContentAsString(), "$.id");

        // 2) Play points
        mockMvc.perform(post("/api/v1/games/tennis/{gameId}/play", gameId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"winner\": \"A\"}"))
                .andExpect(status().isOk());

        // 3) Verify the final state
        mockMvc.perform(get("/api/v1/games/tennis/{gameId}", gameId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.score").exists());
    }

    @Test
    @DisplayName("GET /api/v1/games/tennis/{gameId} should return 404 Not Found when the game does not exist")
    void shouldReturn404ForNonExistentGame() throws Exception {
        UUID nonExistentGameId = UUID.randomUUID();

        mockMvc.perform(get("/api/v1/games/tennis/{gameId}", nonExistentGameId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.error").value("GameNotFound"))
                .andExpect(jsonPath("$.message").value("Game not found with ID: " + nonExistentGameId))
                .andExpect(jsonPath("$.path").value("/api/v1/games/tennis/" + nonExistentGameId));
    }
}
