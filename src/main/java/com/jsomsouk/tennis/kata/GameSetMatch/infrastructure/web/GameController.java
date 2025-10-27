package com.jsomsouk.tennis.kata.GameSetMatch.infrastructure.web;

import com.jsomsouk.tennis.kata.GameSetMatch.application.GameService;
import com.jsomsouk.tennis.kata.GameSetMatch.application.dto.GameDto;
import com.jsomsouk.tennis.kata.GameSetMatch.application.dto.PlayRequest;
import com.jsomsouk.tennis.kata.GameSetMatch.application.dto.ScoreResponse;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/games/tennis")
public class GameController {
    private GameService gameService;
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public GameDto createGame() {
        return gameService.createGame();
    }

    @PostMapping("/{gameId}/play")
    public ScoreResponse playBall(
            @PathVariable UUID gameId,
            @RequestBody PlayRequest request) {
        return gameService.playBall(gameId, request.winner());
    }

    @GetMapping("/{gameId}")
    public ScoreResponse getScore(@PathVariable UUID gameId) {
        return gameService.getScore(gameId);
    }
}
