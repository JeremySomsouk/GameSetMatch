package com.jsomsouk.tennis.kata.GameSetMatch.domain.score;

import com.jsomsouk.tennis.kata.GameSetMatch.domain.Player;

public record Thirty() implements PlayerScore {
    @Override
    public PlayerScore nextPoint(Player pointWinner) {
        return new Forty(pointWinner);
    }

    @Override
    public String toString() {
        return "30";
    }
}
