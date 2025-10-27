package com.jsomsouk.tennis.kata.GameSetMatch.domain.score;

public sealed interface PlayerScore permits Zero, Fifteen, Thirty, Forty, Deuce, Advantage, GameWon {
    PlayerScore nextPoint();
}
