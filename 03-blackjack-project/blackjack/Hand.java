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

    // built in with ArrayList that removes all elements 
    public void clear() {
        cards.clear();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public int size() {
        return cards.size();
    }

    public int getValue() {
        int total = 0;
        int aces = 0;

        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            total += card.getValue();
        }
        // in the case that your total would bust but you have an ace
        // this lets us change the value of an ace from 11 to 1
        while (total > 21 && aces > 0) {
            total -= 10;
            aces--;
        }

        return total;
    }

    public boolean isBust() {
        return getValue() > 21;
    }

    public boolean isBlackJack() {
        return getValue() == 21;
    }

    @Override 
    public String toString() {
        StringBuilder stringbuilder = new StringBuilder();
        for (Card card : cards) {
            stringbuilder.append(card).append("\n\n");
        }
        stringbuilder.append("Total: ").append(getValue());
        return stringbuilder.toString();
    }
}