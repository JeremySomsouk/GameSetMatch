package com.jsomsouk.tennis.kata.GameSetMatch.domain.score;

import com.jsomsouk.tennis.kata.GameSetMatch.domain.Player;

public record Zero() implements PlayerScore {
    @Override
    public PlayerScore nextPoint(Player pointWinner) {
        return new Fifteen();
    }

    @Override
    public String toString() {
        return "0";
    }
}
