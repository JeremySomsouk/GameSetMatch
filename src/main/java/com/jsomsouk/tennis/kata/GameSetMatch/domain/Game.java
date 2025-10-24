package com.jsomsouk.tennis.kata.GameSetMatch.domain;

import com.jsomsouk.tennis.kata.GameSetMatch.domain.score.GameWon;
import com.jsomsouk.tennis.kata.GameSetMatch.domain.score.PlayerScore;

public class Game {
    private PlayerScore playerScoreA;
    private PlayerScore playerScoreB;

    public void playBall(Player winner) {
        if (winner == Player.A) {
            this.playerScoreA = this.playerScoreA.nextPoint(winner);
        } else {
            this.playerScoreB = this.playerScoreB.nextPoint(winner);
        }
    }

    public String getCurrentScore() {
        return "Player A: %s / Player B: %s".formatted(playerScoreA, playerScoreB);
    }

    public boolean isGameWon() {
        return playerScoreA instanceof GameWon
                || playerScoreB instanceof GameWon;
    }
}
