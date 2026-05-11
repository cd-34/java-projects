package blackjack;

public class Blackjack {
    private Deck deck;
    private Player[] players;
    private Dealer dealer;
    private ConsoleIO consoleIO;

    public Blackjack() {
        this.deck = new Deck();
        this.deck.shuffle();
        this.dealer = new Dealer();
    }

    public void init(Player[] players) {
        this.players = players;
        // next 5 lines are just temporary so I can see the deck being shuffled and printed out
        // this.deck = new Deck();
        // this.deck.shuffle();
        // for (Card card : deck.getCards()) {
        //     System.out.println(card + "\n");
        // }

        // at the start of every blackjack game
        // dealer gets one card, player gets 2 cards
        // should print that out for now and make prettier with stringbuilder later
        dealer.addCard(deck.deal());
        System.out.println("Dealer's hand: \n" + dealer.getHand());

        for (int i = 0; i < players.length; i++) {
            players[i].addCard(deck.deal());
            players[i].addCard(deck.deal());
            System.out.println(players[i].getName() + "'s hand: \n" + players[i].getHand());
        }
        dealer.playTurn(deck);
    }
}