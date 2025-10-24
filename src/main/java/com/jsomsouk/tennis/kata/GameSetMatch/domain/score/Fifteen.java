package com.jsomsouk.tennis.kata.GameSetMatch.domain.score;

public record Fifteen() implements PlayerScore {
    @Override
    public PlayerScore nextPoint() {
        return new Thirty();
    }

    @Override
    public String toString() {
        return "15";
    }
}
