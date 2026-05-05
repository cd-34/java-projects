package blackjack;

public class Card {
    // should store a card's suit + rank
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }
}