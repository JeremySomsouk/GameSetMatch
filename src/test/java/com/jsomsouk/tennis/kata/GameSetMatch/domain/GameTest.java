package com.jsomsouk.tennis.kata.GameSetMatch.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayName("Domain - Game")
class GameTest {

    @Nested
    @DisplayName("Standard game progression")
    class StandardGameProgression {
        @Test
        @DisplayName("Game starts with zero")
        void gameStartsWithZero() {
            assertThat(new Game().getCurrentScore()).isEqualTo("Player A: 0 / Player B: 0");
        }

        @ParameterizedTest(name = "Player {0} wins after scoring 4 points in a row")
        @EnumSource(Player.class)
        void playerWinsAfterFourPointsInARow(Player winner) {
            // Given
            Game game = new Game();

            // When
            game.playBall(winner);
            game.playBall(winner);
            game.playBall(winner);
            game.playBall(winner);

            // then
            assertSoftly(softly -> {
                softly.assertThat(game.getCurrentScore())
                        .isEqualTo("Player %s wins the game", winner.name());
                softly.assertThat(game.isGameWon()).isTrue();
                softly.assertThat(game.getWinner()).isEqualTo(winner);
            });
        }
    }

    @Nested
    @DisplayName("Deuce scenarios")
    class DeuceScenarios {

        @Test
        @DisplayName("Game reaches deuce (40-40) when both players have scored 3 times")
        void gameReachesDeuce() {
            // Given
            Game game = new Game();

            // When
            game.playBall(Player.A); // 15-0
            game.playBall(Player.B); // 15-15
            game.playBall(Player.A); // 30-15
            game.playBall(Player.B); // 30-30
            game.playBall(Player.A); // 40-30
            game.playBall(Player.B); // 40-40 (Deuce)

            // Then
            assertThat(game.getCurrentScore()).isEqualTo("40-40");
        }

        @Test
        @DisplayName("Player A wins from advantage after deuce")
        void playerAWinsFromAdvantageAfterDeuce() {
            // Given
            Game game = new Game();
            simulateDeuce(game);

            // When
            game.playBall(Player.A); // Advantage A
            game.playBall(Player.A); // Game A

            // Then
            assertSoftly(softly -> {
                softly.assertThat(game.getCurrentScore())
                        .contains("Player A wins the game");
                softly.assertThat(game.getWinner()).isEqualTo(Player.A);
            });
        }

        @Test
        @DisplayName("Game returns to deuce if opponent scores on advantage")
        void gameReturnsToDeuceFromAdvantage() {
            // Given
            Game game = new Game();
            simulateDeuce(game);

            // When
            game.playBall(Player.A);  // Advantage A
            game.playBall(Player.B);  // Deuce
            game.playBall(Player.A);  // Advantage A
            game.playBall(Player.B);  // Deuce
            game.playBall(Player.A);  // Advantage A
            game.playBall(Player.B);  // Deuce
            game.playBall(Player.A);  // Advantage A
            game.playBall(Player.B);  // Deuce

            // Then
            assertThat(game.getCurrentScore()).contains("40-40");
        }
    }

    @Nested
    @DisplayName("Edge cases")
    class EdgeCases {

        @Test
        @DisplayName("Game does not allow null player")
        void nullPlayerThrowsException() {
            Game game = new Game();
            assertThatThrownBy(() -> game.playBall(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Winner cannot be null");
        }
    }

    // Helper method to simulate a deuce (40-40)
    private void simulateDeuce(Game game) {
        game.playBall(Player.A); // 15-0
        game.playBall(Player.B); // 15-15
        game.playBall(Player.A); // 30-15
        game.playBall(Player.B); // 30-30
        game.playBall(Player.A); // 40-30
        game.playBall(Player.B); // 40-40 (Deuce)
    }
}
