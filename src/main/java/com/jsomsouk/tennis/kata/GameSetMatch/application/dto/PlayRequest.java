package com.jsomsouk.tennis.kata.GameSetMatch.application.dto;

import com.jsomsouk.tennis.kata.GameSetMatch.domain.Player;

public record PlayRequest(Player winner) {
}
