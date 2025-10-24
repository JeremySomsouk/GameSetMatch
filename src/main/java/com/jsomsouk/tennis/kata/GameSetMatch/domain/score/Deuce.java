package com.jsomsouk.tennis.kata.GameSetMatch.domain.score;

import com.jsomsouk.tennis.kata.GameSetMatch.domain.Player;

public record Deuce() implements PlayerScore {

    @Override
    public PlayerScore nextPoint(Player pointWinner) {
        return new Advantage(pointWinner);
    }

    @Override
    public String toString() {
        return "40-40";
    }
}
