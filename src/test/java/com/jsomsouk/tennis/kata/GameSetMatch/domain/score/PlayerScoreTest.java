package com.jsomsouk.tennis.kata.GameSetMatch.domain.score;

import com.jsomsouk.tennis.kata.GameSetMatch.domain.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Player Score")
class PlayerScoreTest {

    @Test
    @DisplayName("toString() displays the score correctly")
    void toStringDisplaysScore() {
        assertThat(new Zero()).hasToString("0");
        assertThat(new Fifteen()).hasToString("15");
        assertThat(new Thirty()).hasToString("30");
        assertThat(new Forty(Player.A)).hasToString("40");
        assertThat(new Deuce()).hasToString("40-40");
        assertThat(new Advantage(Player.A)).hasToString("Advantage A");
        assertThat(new GameWon(Player.A)).hasToString("Player A wins the game");
    }
}
