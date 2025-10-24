package com.jsomsouk.tennis.kata.GameSetMatch.domain.score;

import com.jsomsouk.tennis.kata.GameSetMatch.domain.Player;

public sealed interface PlayerScore permits Zero, Fifteen, Thirty, Forty, Deuce, Advantage, GameWon {
    PlayerScore nextPoint(Player pointWinner);
}
