package com.jsomsouk.tennis.kata.GameSetMatch.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Score Transitions")
public class ScoreTransitionsTest {

    @Nested
    @DisplayName("Zero")
    class ZeroTest {
        @Test
        @DisplayName("transitions to fifteen when a player scores")
        void transitionsToFifteen() {
            // Given
            final PlayerScore zero = new Zero();

            // When
            final PlayerScore next = zero.nextPoint();

            // Then
            assertThat(next)
                    .isInstanceOf(Fifteen.class)
                    .hasToString("15");
        }
    }

    @Nested
    @DisplayName("Fifteen")
    class FifteenTest {
        @Test
        @DisplayName("transitions to thirty when a player scores")
        void transitionsToThirty() {
            // Given
            final PlayerScore fifteen = new Fifteen();

            // When
            final PlayerScore next = fifteen.nextPoint();

            // then
            assertThat(next)
                    .isInstanceOf(Thirty.class)
                    .hasToString("30");
        }
    }

    @Nested
    @DisplayName("Thirty")
    class ThirtyTest {
        @Test
        @DisplayName("transitions to forty when a player scores")
        void transitionsToForty() {
            // Givne
            final PlayerScore thirty = new Thirty();

            // When
            final PlayerScore next = thirty.nextPoint();

            // Then
            assertThat(next)
                    .isInstanceOf(Forty.class)
                    .hasToString("40");
        }
    }

    @Nested
    @DisplayName("Forty")
    class FortyTest {
        @Test
        @DisplayName("transitions to win")
        void transitionsFromForty() {
            // Given
            final PlayerScore forty = new Forty();

            // When
            final PlayerScore next = forty.nextPoint();

            // Then
            assertThat(next).isInstanceOf(GameWon.class);
        }
    }

    @Nested
    @DisplayName("Deuce")
    class DeuceTest {
        @Test
        @DisplayName("transitions to advantage for the scoring player")
        void transitionsToAdvantage() {
            // Given
            final PlayerScore deuce = new Deuce();

            // When
            final PlayerScore next = deuce.nextPoint();

            // Then
            assertThat(next).isInstanceOf(Advantage.class);
        }
    }

    @Nested
    @DisplayName("Advantage")
    class AdvantageTest {
        @Test
        @DisplayName("transitions to advantage for the scoring player")
        void transitionsFromAdvantage() {
            // Given
            final PlayerScore advantage = new Advantage();
            // When
            final PlayerScore next = advantage.nextPoint();
            // Then
            assertThat(next).isInstanceOf(GameWon.class);
        }
    }

    @Nested
    @DisplayName("GameWon")
    class GameWonTest {
        @Test
        @DisplayName("no transition when already won")
        void isTerminalScoreState() {
            // When
            final PlayerScore gameWon = new GameWon().nextPoint();
            // Then
            assertThat(gameWon).isInstanceOf(GameWon.class);
        }
    }
}
