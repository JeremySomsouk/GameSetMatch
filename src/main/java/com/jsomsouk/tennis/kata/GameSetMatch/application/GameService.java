package com.jsomsouk.tennis.kata.GameSetMatch.application;

import com.jsomsouk.tennis.kata.GameSetMatch.domain.Game;
import com.jsomsouk.tennis.kata.GameSetMatch.domain.Player;
import com.jsomsouk.tennis.kata.GameSetMatch.application.ports.ScoreboardRepository;

import java.util.List;
import java.util.UUID;

public class GameService {
    private final ScoreboardRepository repository;

    public GameService(ScoreboardRepository repository) {
        this.repository = repository;
    }

    public UUID createGame() {
        final var game = new Game();
        repository.save(game);
        return game.getId();
    }

    public void playBall(UUID gameId, Player winner) {
        final var game = repository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("Game not found"));
        game.playBall(winner);
        repository.save(game);
    }

    public String getScore(UUID gameId) {
        return repository.findById(gameId)
                .map(Game::getCurrentScore)
                .orElseThrow(() -> new IllegalArgumentException("Game not found"));
    }

    public List<Game> getAllGames() {
        return repository.findAll();
    }
}
