package blackjack;

public class Card {
    // class should store a card's suit + rank
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getValue() {
        return rank.getValue();
    }

    @Override
    public String toString() {
        String suitSymbol;

        switch (getSuit()) {
            case CLUBS:
                suitSymbol = "♣";
                break;
            case HEARTS:
                suitSymbol = "♥";
                break;
            case SPADES:
                suitSymbol = "♠";
                break;
            case DIAMONDS:
                suitSymbol = "♦";
                break;
            default:
                suitSymbol = "?";
        }

        return 
            "|----|\n" +
            // formats to give an extra space if rank is not 10
            // if rank is 10 then it's two characters, so there's no extra space
            "|" + String.format("%-2s", rank) + " " + suitSymbol + "|\n" +
            "|----|";
    }
}