package com.jsomsouk.tennis.kata.GameSetMatch.infrastructure.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsomsouk.tennis.kata.GameSetMatch.application.GameService;
import com.jsomsouk.tennis.kata.GameSetMatch.application.dto.GameDto;
import com.jsomsouk.tennis.kata.GameSetMatch.application.dto.PlayRequest;
import com.jsomsouk.tennis.kata.GameSetMatch.application.dto.ScoreResponse;
import com.jsomsouk.tennis.kata.GameSetMatch.domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameController.class)
@DisplayName("Infrastructure - Controller tests")
class GameControllerTest {
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Mock
    private GameService gameService;
    @InjectMocks
    private GameController gameController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();
    }

    @Test
    @DisplayName("POST /api/v1/games/tennis should create a new game and return its ID and initial score")
    void createGame_shouldReturnGameDto() throws Exception {
        // Given
        final var testId = UUID.randomUUID();
        final var mockDto = new GameDto(testId, "0-0");

        when(gameService.createGame()).thenReturn(mockDto);

        // When / Then
        mockMvc.perform(post("/api/v1/games/tennis"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testId.toString()))
                .andExpect(jsonPath("$.score").value("0-0"));
    }

    @Test
    @DisplayName("POST /api/v1/games/tennis/{gameId}/play should update score when valid player scores")
    void playBall_shouldReturnScoreResponse() throws Exception {
        // Given
        final var gameId = UUID.randomUUID();
        final var request = new PlayRequest(Player.A);
        final var response = new ScoreResponse("15-0", null);

        given(gameService.playBall(gameId, Player.A)).willReturn(response);

        // When/Then
        mockMvc.perform(post("/api/v1/games/tennis/{gameId}/play", gameId)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.score").value("15-0"));
    }

    @Test
    @DisplayName("GET /api/v1/games/tennis/{gameId} should return current score for existing game")
    void getScore_shouldReturnScoreResponse() throws Exception {
        // Given
        final var gameId = UUID.randomUUID();
        final var response = new ScoreResponse("30-0", null);

        given(gameService.getScore(gameId)).willReturn(response);

        // When/Then
        mockMvc.perform(get("/api/v1/games/tennis/{gameId}", gameId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.score").value("30-0"));
    }

    @Test
    @DisplayName("POST /api/v1/games/tennis/{gameId}/play should return 400 Bad Request when player is invalid")
    void playBall_shouldReturn400ForInvalidPlayer() throws Exception {
        final var randomGameId = UUID.randomUUID();

        // When / Then
        mockMvc.perform(post("/api/v1/games/tennis/{gameId}/play", randomGameId)
                        .contentType("application/json")
                        .content("{\"winner\": \"INVALID\"}"))
                .andExpect(status().isBadRequest());
    }
}
