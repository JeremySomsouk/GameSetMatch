package com.jsomsouk.tennis.kata.GameSetMatch.domain;

import com.jsomsouk.tennis.kata.GameSetMatch.domain.score.*;

import java.util.UUID;

public class Game {
    private final UUID id;
    private PlayerScore playerScoreA;
    private PlayerScore playerScoreB;

    public Game() {
        this(UUID.randomUUID(), new Zero(), new Zero());
    }

    public Game(UUID id, PlayerScore playerScoreA, PlayerScore playerScoreB) {
        this.id = id;
        this.playerScoreA = playerScoreA;
        this.playerScoreB = playerScoreB;
    }

    public void playBall(Player winner) {
        if (winner == null) {
            throw new IllegalArgumentException("Winner cannot be null");
        }

        // Both players go to Deuce when the opponent loses advantage
        if (hasOpponentAdvantage(winner)) {
            playerScoreA = new Deuce();
            playerScoreB = new Deuce();
            return;
        }

        // Normal point progression
        if (winner == Player.A) {
            playerScoreA = playerScoreA.nextPoint();
        } else {
            playerScoreB = playerScoreB.nextPoint();
        }
        handleDeuceCondition();
    }

    public String getCurrentScore() {
        if (playerScoreA instanceof Deuce && playerScoreB instanceof Deuce) {
            return "40-40";
        }
        if (playerScoreA instanceof GameWon) {
            return "Player A wins the game";
        } else if (playerScoreB instanceof GameWon) {
            return "Player B wins the game";
        }

        return "Player A: %s / Player B: %s".formatted(playerScoreA, playerScoreB);
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

    public UUID getId() {
        return id;
    }
}
