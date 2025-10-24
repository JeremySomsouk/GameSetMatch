package com.jsomsouk.tennis.kata.GameSetMatch.domain.score;

import com.jsomsouk.tennis.kata.GameSetMatch.domain.Player;

public record Forty(Player player) implements PlayerScore {
    // Means that one player is at forty, not the other one. Or else use deuce.
    @Override
    public PlayerScore nextPoint(Player pointWinner) {
        if (pointWinner == player) {
            return new GameWon(player);
        } else {
            return new Deuce();
        }
    }

    @Override
    public String toString() {
        return "40";
    }
}
