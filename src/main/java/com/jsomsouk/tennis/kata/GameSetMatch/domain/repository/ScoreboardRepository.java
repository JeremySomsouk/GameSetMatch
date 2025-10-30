package com.jsomsouk.tennis.kata.GameSetMatch.domain.repository;

import com.jsomsouk.tennis.kata.GameSetMatch.domain.model.Game;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ScoreboardRepository {
    void save(Game game);
    Optional<Game> findById(UUID id);
    List<Game> findAll();
}
