package com.jsomsouk.tennis.kata.GameSetMatch.domain.score;

public record Advantage() implements PlayerScore {
    @Override
    public PlayerScore nextPoint() {
        return new GameWon();
    }
}
