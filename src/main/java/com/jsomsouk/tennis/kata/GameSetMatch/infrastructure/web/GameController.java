package com.jsomsouk.tennis.kata.GameSetMatch.infrastructure.web;

import com.jsomsouk.tennis.kata.GameSetMatch.application.GameService;
import com.jsomsouk.tennis.kata.GameSetMatch.application.dto.GameDto;
import com.jsomsouk.tennis.kata.GameSetMatch.application.dto.PlayRequest;
import com.jsomsouk.tennis.kata.GameSetMatch.application.dto.ScoreResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/games/tennis")
public class GameController {
    private GameService gameService;
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GameDto> createGame() {
        final var created = gameService.createGame();
        final var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PostMapping("/{gameId}/play")
    public ResponseEntity<ScoreResponse> playBall(
            @PathVariable UUID gameId,
            @RequestBody PlayRequest request) {
        return ResponseEntity.ok(gameService.playBall(gameId, request.winner()));
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<ScoreResponse> getScore(@PathVariable UUID gameId) {
        return ResponseEntity.ok(gameService.getScore(gameId));
    }
}
