package blackjack;

public class Blackjack {
    public void init() {
        // next 5 lines are just temporary so I can see the deck being shuffled and printed out
        Deck deck = new Deck();
        deck.shuffle();
        for (Card card : deck.getCards()) {
            System.out.println(card + "\n");
        }

        // temporarily add two cards to player1's hand then print them out to test
        // remove later
        Deck deck = new Deck();
        deck.shuffle();
        System.out.println(deck.size()); // should be 52
        players[0].addCard(deck.deal());
        players[0].addCard(deck.deal());
        System.out.println(players[0].getName() + "'s hand:");
        System.out.println(players[0].getHand());
        System.out.println(deck.size()); // should be 50
    }
}