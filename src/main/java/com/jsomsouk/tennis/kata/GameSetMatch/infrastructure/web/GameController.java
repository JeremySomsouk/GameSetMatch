package com.jsomsouk.tennis.kata.GameSetMatch.infrastructure.web;

import com.jsomsouk.tennis.kata.GameSetMatch.application.GameService;
import com.jsomsouk.tennis.kata.GameSetMatch.application.dto.GameDto;
import com.jsomsouk.tennis.kata.GameSetMatch.application.dto.PlayRequest;
import com.jsomsouk.tennis.kata.GameSetMatch.application.dto.ScoreResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/games/tennis")
@Slf4j
@RequiredArgsConstructor
public class GameController {
    private GameService gameService;

    @PostMapping
    public GameDto createGame() {
        final var dto = gameService.createGame();
        log.info("Created game with ID: {} and score: {}", dto.id(), dto.score());
        return ResponseEntity.ok(dto).getBody();
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
