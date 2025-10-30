package com.jsomsouk.tennis.kata.GameSetMatch;

import com.jsomsouk.tennis.kata.GameSetMatch.application.GameService;
import com.jsomsouk.tennis.kata.GameSetMatch.domain.model.Player;
import com.jsomsouk.tennis.kata.GameSetMatch.infrastructure.persistence.InMemoryScoreboardRepository;

public class SimpleGame {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: ./gradlew runKata -PkataArgs=\"<sequence>\"");
            System.out.println("Example: ./gradlew runKata -PkataArgs=\"AAAA\"");
            return;
        }

        playGame(args[0]);
    }

    public static void playGame(String sequence) {
        final var repository = new InMemoryScoreboardRepository();
        final var gameService = new GameService(repository);
        final var gameId = gameService.createGame().id();

        System.out.println("0-0");
        for (char c : sequence.toUpperCase().toCharArray()) {
            if (c != 'A' && c != 'B') {
                System.out.println("Invalid input: only 'A' or 'B' allowed");
                return;
            }

            final var winner = c == 'A' ? Player.A : Player.B;
            final var score = gameService.playBall(gameId, winner);
            System.out.println(score.score());

            if (score.winner() != null) {
                System.out.println("The game has ended, feel free to play again!");
                return;
            }
        }
    }
}
