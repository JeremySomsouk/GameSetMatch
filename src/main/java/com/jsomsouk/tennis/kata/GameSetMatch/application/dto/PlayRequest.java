package com.jsomsouk.tennis.kata.GameSetMatch.application.dto;

import com.jsomsouk.tennis.kata.GameSetMatch.domain.model.Player;

public record PlayRequest(Player winner) {
}
