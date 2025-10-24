package com.jsomsouk.tennis.kata.GameSetMatch.domain.score;

import com.jsomsouk.tennis.kata.GameSetMatch.domain.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

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
            final PlayerScore next = zero.nextPoint(Player.A);

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
            final PlayerScore next = fifteen.nextPoint(Player.A);

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
            final PlayerScore next = thirty.nextPoint(Player.A);

            // Then
            assertThat(next)
                    .isInstanceOf(Forty.class)
                    .hasToString("40");
        }
    }

    @Nested
    @DisplayName("Forty")
    class FortyTest {
        @ParameterizedTest(name = "transitions to {0} when {1} scores")
        @MethodSource("fortyTransitions")
        void transitionsFromForty(
                Class<? extends PlayerScore> expectedScoreClass,
                Player scorer,
                Player fortyPlayer) {
            // Given
            final PlayerScore forty = new Forty(fortyPlayer);

            // When
            final PlayerScore next = forty.nextPoint(scorer);

            // Then
            assertThat(next).isInstanceOf(expectedScoreClass);
        }

        static Stream<Arguments> fortyTransitions() {
            return Stream.of(
                    // The same player scores: wins
                    Arguments.of(GameWon.class, Player.A, Player.A),
                    // The opponent scores: deuce
                    Arguments.of(Deuce.class, Player.B, Player.A)
            );
        }
    }

    @Nested
    @DisplayName("Deuce")
    class DeuceTest {
        @Test
        @DisplayName("transitions to advantage for the scoring player")
        void transitionsToAdvantage() {
            PlayerScore deuce = new Deuce();
            PlayerScore next = deuce.nextPoint(Player.A);
            assertThat(next)
                    .isInstanceOf(Advantage.class)
                    .hasToString("Advantage A");
        }
    }

    @Nested
    @DisplayName("Advantage")
    class AdvantageTest {
        @ParameterizedTest(name = "transitions to {0} when {1} scores (advantage for A)")
        @MethodSource("advantageTransitions")
        void transitionsFromAdvantage(
                Class<? extends PlayerScore> expectedScoreClass,
                Player scorer) {
            PlayerScore advantage = new Advantage(Player.A);
            PlayerScore next = advantage.nextPoint(scorer);
            assertThat(next).isInstanceOf(expectedScoreClass);
        }

        static Stream<Arguments> advantageTransitions() {
            return Stream.of(
                    // Player with advantage scores: wins
                    Arguments.of(GameWon.class, Player.A),
                    // The opponent scores: deuce
                    Arguments.of(Deuce.class, Player.B)
            );
        }
    }

    @Nested
    @DisplayName("GameWon")
    class GameWonTest {
        @ParameterizedTest(name = "GameWon for {0} does not change on further points")
        @EnumSource(Player.class)
        void isTerminalForPlayerX(Player player) {
            PlayerScore gameWon = new GameWon(player).nextPoint(player);
            assertThat(gameWon)
                    .isInstanceOf(GameWon.class)
                    .hasToString("Player %s wins the game".formatted(player));
        }
    }
}
