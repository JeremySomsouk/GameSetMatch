package com.jsomsouk.tennis.kata.GameSetMatch.domain.model.score;

public record Deuce() implements PlayerScore {

    @Override
    public PlayerScore nextPoint() {
        return new Advantage();
    }
}
