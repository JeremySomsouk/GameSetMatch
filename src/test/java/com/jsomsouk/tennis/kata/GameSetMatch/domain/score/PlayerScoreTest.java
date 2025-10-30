package com.jsomsouk.tennis.kata.GameSetMatch.domain.score;

import com.jsomsouk.tennis.kata.GameSetMatch.domain.model.score.Fifteen;
import com.jsomsouk.tennis.kata.GameSetMatch.domain.model.score.Forty;
import com.jsomsouk.tennis.kata.GameSetMatch.domain.model.score.Thirty;
import com.jsomsouk.tennis.kata.GameSetMatch.domain.model.score.Zero;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Domain - Player Score")
class PlayerScoreTest {

    @Test
    @DisplayName("toString() displays the score correctly")
    void toStringDisplaysScore() {
        assertThat(new Zero()).hasToString("0");
        assertThat(new Fifteen()).hasToString("15");
        assertThat(new Thirty()).hasToString("30");
        assertThat(new Forty()).hasToString("40");
    }
}
