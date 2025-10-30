package com.jsomsouk.tennis.kata.GameSetMatch.domain.model.score;

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
