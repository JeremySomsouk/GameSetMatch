package com.jsomsouk.tennis.kata.GameSetMatch.application.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record GameDto(UUID id, String score) {
    @JsonCreator
    public GameDto(
            @JsonProperty("id") UUID id,
            @JsonProperty("score") String score
    ) {
        this.id = id;
        this.score = score;
    }
}
