package blackjack;

public class Blackjack {
    private Deck deck;
    private Player[] players;
    private Dealer dealer;

    public Blackjack() {
        this.deck = new Deck();
        this.deck.shuffle();
        this.dealer = new Dealer();
    }

    public void init(Player[] players) {
        this.players = players;
        // next 5 lines are just so I can see the deck being shuffled and printed out
        // this.deck = new Deck();
        // this.deck.shuffle();
        // for (Card card : deck.getCards()) {
        //     System.out.println(card + "\n");
        // }

        // at the start of every blackjack game
        // dealer gets one card, player gets 2 cards
        // should print that out for now and make prettier with stringbuilder? later
        dealer.addCard(deck.deal());
        System.out.println("Dealer's hand: \n" + dealer.getHand());

        for (int i = 0; i < players.length; i++) {
            players[i].addCard(deck.deal());
            players[i].addCard(deck.deal());
            System.out.println(players[i].getName() + "'s hand: \n" + players[i].getHand());
        }
    }

    public void dealerTurn() {
        dealer.playTurn(deck);
    }

    public void dealCardToPlayer(int i) {
        Card card = deck.deal();
        players[i].addCard(card);
        System.out.println(card);
    }

    public void resetRound() {
        for (int i = 0; i < players.length; i++) {
            players[i].clearHand();
        }
        dealer.clearHand();
        deck = new Deck();
        deck.shuffle();
    }

    public void determineWinner() {
        int dealerValue = dealer.getHandValue();
        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            int playerValue = player.getHandValue();

            if (player.isPlayerBust()) {
                System.out.println(player.getName() + "busts. Dealer wins!");
            } else if (dealer.isDealerBust() || playerValue > dealerValue) {
                System.out.println(player.getName() + " wins!");
                player.incrementWins();
            } else if (playerValue == dealerValue) {
                System.out.println(player.getName() + " ties with the dealer!");
            } else {
                System.out.println(player.getName() + " loses!");
            }
        }
    }

    public void printScores() {
        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i].getName() + "'s wins: " + players[i].getWins());
        }
    }
}