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

        if (winner == Player.A) {
            playerScoreA = playerScoreA.nextPoint(winner);
            if (playerScoreB instanceof Advantage) {
                playerScoreB = new Deuce();
            }
        } else {
            playerScoreB = playerScoreB.nextPoint(winner);
            if (playerScoreA instanceof Advantage) {
                playerScoreA = new Deuce();
            }
        }

        if (playerScoreA instanceof Forty && playerScoreB instanceof Forty) {
            playerScoreA = new Deuce();
            playerScoreB = new Deuce();
        }
    }

    public String getCurrentScore() {
        if (playerScoreA instanceof Deuce && playerScoreB instanceof Deuce) {
            return playerScoreA.toString();
        }
        if (playerScoreA instanceof GameWon gameWonA) {
            return gameWonA.toString();
        } else if (playerScoreB instanceof GameWon gameWonB) {
            return gameWonB.toString();
        }

        return "Player A: %s / Player B: %s".formatted(playerScoreA, playerScoreB);
    }

    public boolean isGameWon() {
        return playerScoreA instanceof GameWon
                || playerScoreB instanceof GameWon;
    }

    public Player getWinner() {
        if (playerScoreA instanceof GameWon(Player playerA)) {
            return playerA;
        } else if (playerScoreB instanceof GameWon(Player playerB)) {
            return playerB;
        }
        return null;
    }
}
