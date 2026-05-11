package blackjack;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    // built in with ArrayList that clears all elements from the list
    public void clear() {
        cards.clear();
    }
}