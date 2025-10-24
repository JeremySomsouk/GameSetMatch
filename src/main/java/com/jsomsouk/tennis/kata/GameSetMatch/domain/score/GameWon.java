package com.jsomsouk.tennis.kata.GameSetMatch.domain.score;

import com.jsomsouk.tennis.kata.GameSetMatch.domain.Player;

public record GameWon(Player player) implements PlayerScore {
    public PlayerScore nextPoint(Player pointWinner) {
        // The game is already won; no further state changes.
        return this;
    }

    @Override
    public String toString() {
        return "Player %s wins the game".formatted(player);
    }
}
