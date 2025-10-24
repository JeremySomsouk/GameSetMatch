package com.jsomsouk.tennis.kata.GameSetMatch.domain.score;

import com.jsomsouk.tennis.kata.GameSetMatch.domain.Player;

public record Fifteen() implements PlayerScore {
    @Override
    public PlayerScore nextPoint(Player pointWinner) {
        return new Thirty();
    }

    @Override
    public String toString() {
        return "15";
    }
}
