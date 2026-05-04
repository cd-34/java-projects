package blackjack;

public enum Gamestate {
    IN_PROGRESS,
    PLAYER_WIN,
    PLAYER_LOSS,
    TIE // maybe should just count as PLAYER_LOSS? might remove
}