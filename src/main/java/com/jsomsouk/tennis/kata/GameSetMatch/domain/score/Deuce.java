package com.jsomsouk.tennis.kata.GameSetMatch.domain.score;

public record Deuce() implements PlayerScore {

    @Override
    public PlayerScore nextPoint() {
        return new Advantage();
    }

    @Override
    public String toString() {
        return "40-40";
    }
}
