package blackjack;

public class Blackjack {
    public void init() {
        // next 5 lines are just temporary so I can see the deck being shuffled and printed out
        Deck deck = new Deck();
        deck.shuffle();
        for (Card card : deck.getCards()) {
            System.out.println(card + "\n");
        }
    }
}