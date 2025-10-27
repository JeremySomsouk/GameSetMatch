package com.jsomsouk.tennis.kata.GameSetMatch.application;

import com.jsomsouk.tennis.kata.GameSetMatch.application.dto.GameDto;
import com.jsomsouk.tennis.kata.GameSetMatch.application.dto.ScoreResponse;
import com.jsomsouk.tennis.kata.GameSetMatch.application.ports.ScoreboardRepository;
import com.jsomsouk.tennis.kata.GameSetMatch.domain.Game;
import com.jsomsouk.tennis.kata.GameSetMatch.domain.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameService {
    private final ScoreboardRepository scoreboard;

    public GameDto createGame() {
        final var game = new Game();
        scoreboard.save(game);
        return new GameDto(game.getId(), game.getCurrentScore());
    }

    public ScoreResponse playBall(UUID gameId, Player winner) {
        final var game = getGame(gameId).orElseThrow(() ->
                new IllegalArgumentException("Game not found"));

        game.playBall(winner);
        scoreboard.save(game);

        return new ScoreResponse(
                game.getCurrentScore(),
                game.getWinner()
        );
    }

    public ScoreResponse getScore(UUID gameId) {
        final var game = getGame(gameId)
                .orElseThrow(() -> new IllegalArgumentException("Game not found"));

        return new ScoreResponse(
                game.getCurrentScore(),
                game.getWinner()
        );
    }

    public Optional<Game> getGame(UUID gameId) {
        return scoreboard.findById(gameId);
    }
}
