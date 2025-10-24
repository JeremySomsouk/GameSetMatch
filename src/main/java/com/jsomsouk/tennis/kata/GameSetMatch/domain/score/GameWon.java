package com.jsomsouk.tennis.kata.GameSetMatch.domain.score;

public record GameWon() implements PlayerScore {
    public PlayerScore nextPoint() {
        return this;
    }
}
