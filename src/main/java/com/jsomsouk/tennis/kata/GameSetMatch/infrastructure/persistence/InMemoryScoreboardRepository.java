package com.jsomsouk.tennis.kata.GameSetMatch.infrastructure.persistence;

import com.jsomsouk.tennis.kata.GameSetMatch.domain.repository.ScoreboardRepository;
import com.jsomsouk.tennis.kata.GameSetMatch.domain.model.Game;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryScoreboardRepository implements ScoreboardRepository {
    private final Map<UUID, Game> cache = new ConcurrentHashMap<>();

    @Override
    public void save(Game game) {
        cache.put(game.getId(), game);
    }

    @Override
    public Optional<Game> findById(UUID gameId) {
        return Optional.ofNullable(cache.get(gameId));
    }

    @Override
    public List<Game> findAll() {
        return new ArrayList<>(cache.values());
    }
}
