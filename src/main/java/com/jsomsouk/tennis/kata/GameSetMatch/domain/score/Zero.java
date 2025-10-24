package com.jsomsouk.tennis.kata.GameSetMatch.domain.score;

public record Zero() implements PlayerScore {
    @Override
    public PlayerScore nextPoint() {
        return new Fifteen();
    }

    @Override
    public String toString() {
        return "0";
    }
}
