package com.jsomsouk.tennis.kata.GameSetMatch.application;

import com.jsomsouk.tennis.kata.GameSetMatch.application.dto.GameDto;
import com.jsomsouk.tennis.kata.GameSetMatch.application.dto.ScoreResponse;
import com.jsomsouk.tennis.kata.GameSetMatch.application.exception.GameNotFound;
import com.jsomsouk.tennis.kata.GameSetMatch.domain.repository.ScoreboardRepository;
import com.jsomsouk.tennis.kata.GameSetMatch.domain.model.Game;
import com.jsomsouk.tennis.kata.GameSetMatch.domain.model.Player;
import com.jsomsouk.tennis.kata.GameSetMatch.domain.model.score.Fifteen;
import com.jsomsouk.tennis.kata.GameSetMatch.domain.model.score.Thirty;
import com.jsomsouk.tennis.kata.GameSetMatch.domain.model.score.Zero;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

@DisplayName("Application - Core engine tests")
@ExtendWith(MockitoExtension.class)
class GameServiceTest {
    @Mock
    private ScoreboardRepository scoreboard;

    @InjectMocks
    private GameService gameService;

    @Test
    @DisplayName("createGame() should create a new game and return its DTO")
    void createGame_shouldCreateNewGameAndReturnDto() {
        // Given
        doNothing().when(scoreboard).save(any(Game.class));

        // When
        GameDto result = gameService.createGame();

        // Then
        assertThat(result).isNotNull();
        assertThat(result.id()).isNotNull();
        assertThat(result.score()).isEqualTo("Player A: 0 / Player B: 0");
        then(scoreboard).should(times(1)).save(any(Game.class));
    }

    @Test
    @DisplayName("playBall() should update game score when game exists")
    void playBall_shouldUpdateGameScore() {
        // Given
        final var gameId = UUID.randomUUID();
        final var mockGame = new Game(gameId, new Fifteen(), new Zero());
        given(scoreboard.findById(gameId)).willReturn(Optional.of(mockGame));

        // When
        ScoreResponse result = gameService.playBall(gameId, Player.A);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.score()).isEqualTo(mockGame.getCurrentScore());
        then(scoreboard).should(times(1)).save(mockGame);
    }

    @Test
    @DisplayName("playBall() should throw exception when game not found")
    void playBall_shouldThrowExceptionWhenGameNotFound() {
        // Given
        final var gameId = UUID.randomUUID();
        given(scoreboard.findById(gameId)).willReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> gameService.playBall(gameId, Player.A))
                .isInstanceOf(GameNotFound.class)
                .hasMessage("Game not found with ID: " + gameId);
    }

    @Test
    @DisplayName("getScore() should return current score when game exists")
    void getScore_shouldReturnCurrentScore() {
        // Given
        final var gameId = UUID.randomUUID();
        final var mockGame = new Game(gameId, new Thirty(), new Zero());
        given(scoreboard.findById(gameId)).willReturn(Optional.of(mockGame));

        // When
        ScoreResponse result = gameService.getScore(gameId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.score()).isEqualTo(mockGame.getCurrentScore());
    }

    @Test
    @DisplayName("getScore() should throw exception when game not found")
    void getScore_shouldThrowExceptionWhenGameNotFound() {
        // Given
        final var gameId = UUID.randomUUID();
        given(scoreboard.findById(gameId)).willReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> gameService.getScore(gameId))
                .isInstanceOf(GameNotFound.class)
                .hasMessage("Game not found with ID: " + gameId);
    }

    @Test
    @DisplayName("getGame() should return game when it exists")
    void getGame_shouldReturnGameWhenExists() {
        // Given
        final var gameId = UUID.randomUUID();
        final var mockGame = new Game();
        given(scoreboard.findById(gameId)).willReturn(Optional.of(mockGame));

        // When
        Optional<Game> result = gameService.getGame(gameId);

        // Then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(mockGame);
    }

    @Test
    @DisplayName("getGame() should return empty when game not found")
    void getGame_shouldReturnEmptyWhenGameNotFound() {
        // Given
        final var gameId = UUID.randomUUID();
        given(scoreboard.findById(gameId)).willReturn(Optional.empty());

        // When
        Optional<Game> result = gameService.getGame(gameId);

        // Then
        assertThat(result).isEmpty();
    }
}