package com.jsomsouk.tennis.kata.GameSetMatch.domain.score;

public record Forty() implements PlayerScore {
    @Override
    public PlayerScore nextPoint() {
        return new GameWon();
    }

    @Override
    public String toString() {
        return "40";
    }
}
