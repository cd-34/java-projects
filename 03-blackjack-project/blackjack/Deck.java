package blackjack;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    // 52 card deck is a ArrayList called "cards"
    // each <Card> is made up of class Card(Rank, Suit)
    private ArrayList<Card> cards;

    // Initializing a new deck builds a 52 card deck as an ArrayList
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

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public int size() {
        return cards.size();
    }

    // returns the last card in the deck list
    // also removes it from the deck
    public Card deal() {
        return cards.remove(cards.size() - 1);
    }
}