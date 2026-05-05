package blackjack;
import java.util.ArrayList;

public class Deck {
    // should contain deal() method
    // 52 card deck is a list called "cards"
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        buildDeck();
    }
    
    // creates a 52 card deck with every value
    private void buildDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public int size() {
        return cards.size();
    }
}