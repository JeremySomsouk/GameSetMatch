package com.jsomsouk.tennis.kata.GameSetMatch.domain.model.score;

public record Thirty() implements PlayerScore {
    @Override
    public PlayerScore nextPoint() {
        return new Forty();
    }

    @Override
    public String toString() {
        return "30";
    }
}
