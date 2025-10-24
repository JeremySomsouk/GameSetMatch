package com.jsomsouk.tennis.kata.GameSetMatch.domain.score;

import com.jsomsouk.tennis.kata.GameSetMatch.domain.Player;

public record Advantage(Player player) implements PlayerScore {
    @Override
    public PlayerScore nextPoint(Player pointWinner) {
        return (player == pointWinner)
                ? new GameWon(pointWinner)
                : new Deuce();
    }

    @Override
    public String toString() {
        return "Advantage " + player;
    }
}
