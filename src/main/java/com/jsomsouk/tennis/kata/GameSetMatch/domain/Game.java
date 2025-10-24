package com.jsomsouk.tennis.kata.GameSetMatch.domain;

import com.jsomsouk.tennis.kata.GameSetMatch.domain.score.*;

public class Game {
    private PlayerScore playerScoreA;
    private PlayerScore playerScoreB;

    public Game() {
        this.playerScoreA = new Zero();
        this.playerScoreB = new Zero();
    }

    public void playBall(Player winner) {
        if (winner == null) {
            throw new IllegalArgumentException("Winner cannot be null");
        }

        if (hasOpponentAdvantage(winner)) {
            // Both players go to Deuce when the opponent loses advantage
            playerScoreA = new Deuce();
            playerScoreB = new Deuce();
        } else {
            // Normal point progression
            if (winner == Player.A) {
                playerScoreA = playerScoreA.nextPoint();
            } else {
                playerScoreB = playerScoreB.nextPoint();
            }
        }

        handleDeuceCondition();
    }

    public String getCurrentScore() {
        return switch (playerScoreA) {
            case Deuce ignored when playerScoreB instanceof Deuce -> "40-40";
            case GameWon ignored -> "Player A wins the game";
            default -> playerScoreB instanceof GameWon
                    ? "Player B wins the game"
                    : String.format("Player A: %s / Player B: %s", playerScoreA, playerScoreB);
        };
    }

    public boolean isGameWon() {
        return playerScoreA instanceof GameWon
                || playerScoreB instanceof GameWon;
    }

    public Player getWinner() {
        if (playerScoreA instanceof GameWon) {
            return Player.A;
        } else if (playerScoreB instanceof GameWon) {
            return Player.B;
        }
        return null;
    }

    private void handleDeuceCondition() {
        if (playerScoreA instanceof Forty && playerScoreB instanceof Forty) {
            playerScoreA = new Deuce();
            playerScoreB = new Deuce();
        }
    }

    private boolean hasOpponentAdvantage(Player winner) {
        return (winner == Player.A)
                ? playerScoreB instanceof Advantage
                : playerScoreA instanceof Advantage;
    }
}
