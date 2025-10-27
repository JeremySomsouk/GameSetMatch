package com.jsomsouk.tennis.kata.GameSetMatch.infrastructure.persistence;

import com.jsomsouk.tennis.kata.GameSetMatch.domain.Game;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Cache tests")
class InMemoryScoreboardRepositoryTest {

    private final InMemoryScoreboardRepository repository = new InMemoryScoreboardRepository();
    private final Game testGame = new Game();

    @Test
    @DisplayName("In memory cache, should save a game")
    void shouldSaveAndRetrieveGame() {
        // Given
        repository.save(testGame);

        // When
        Optional<Game> found = repository.findById(testGame.getId());

        // Then
        assertTrue(found.isPresent());
        assertEquals(testGame.getId(), found.get().getId());
    }

    @Test
    @DisplayName("In memory cache, should return empty when the game was not found")
    void shouldReturnEmptyWhenGameNotFound() {
        assertTrue(repository.findById(UUID.randomUUID()).isEmpty());
    }

    @Test
    @DisplayName("In memory cache, should list all open games")
    void shouldReturnAllGames() {
        // When
        repository.save(testGame);
        repository.save(new Game());

        // Then
        assertEquals(2, repository.findAll().size());
    }
}
