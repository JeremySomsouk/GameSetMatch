package com.jsomsouk.tennis.kata.GameSetMatch.application.exception;

import java.util.UUID;

public class GameNotFound extends RuntimeException {
    public GameNotFound(String gameId) {
        super("Game not found with ID: " + gameId);
    }

    public GameNotFound(UUID gameId) {
        this(gameId != null ? gameId.toString() : "null");
    }
}
