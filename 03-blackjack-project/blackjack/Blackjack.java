package blackjack;

public class Blackjack {
    private Deck deck;
    private Player[] players;
    private Dealer dealer;

    public void init(Player[] players) {
        // next 5 lines are just temporary so I can see the deck being shuffled and printed out
        // this.deck = new Deck();
        // this.deck.shuffle();
        // for (Card card : deck.getCards()) {
        //     System.out.println(card + "\n");
        // }
        this.deck = new Deck();
        this.deck.shuffle();
        this.players = players;

        // at the start of every blackjack game
        // dealer gets one card, player gets 2 cards
        // should print that out for now and make prettier with stringbuilder later
        System.out.println(deck.size()); // should be 52
        System.out.println(deck)
    }
}