package com.jsomsouk.tennis.kata.GameSetMatch;

import com.jsomsouk.tennis.kata.GameSetMatch.application.GameService;
import com.jsomsouk.tennis.kata.GameSetMatch.domain.Player;
import com.jsomsouk.tennis.kata.GameSetMatch.infrastructure.persistence.InMemoryScoreboardRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameSetMatchApplication {

    public static void main(String[] args) {
        // Setup
        final var repository = new InMemoryScoreboardRepository();
        final var gameService = new GameService(repository);

        // Create a new game
        final var gameId = gameService.createGame();
        System.out.println("New game created with ID: " + gameId);

        // Play some balls
        gameService.playBall(gameId, Player.A);
        gameService.playBall(gameId, Player.B);
        gameService.playBall(gameId, Player.A);
        gameService.playBall(gameId, Player.A);

        // Get the current score
        String score = gameService.getScore(gameId);
        System.out.println("Current score: " + score);

        // List all games
        System.out.println("All games: " + gameService.getAllGames());
    }
}
