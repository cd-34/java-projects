package blackjack;

import java.util.Map;
import java.util.LinkedHashMap;

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

    // shallow method that's only used in BlackjackApp's playRound() once
    // however having this as its own method helps with testing 
    public Card dealCardToPlayer(int i) {
        Card card = deck.deal();
        players[i].addCard(card);
        return card;
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
                System.out.println(player.getName() + " busts. Dealer wins!");
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

    // only used once in blackjackapp under run()
    public Map<String, Integer> printScores() {
        Map<String, Integer> scores = new LinkedHashMap<>();
        for (int i = 0; i < players.length; i++) {
            scores.put(players[i].getName(), players[i].getWins());
        }
        // move this to blackjackapp instead?
        for (Map.Entry<String, Integer> entry: scores.entrySet()) {
            System.out.println(entry.getKey() + "'s wins: " + entry.getValue());
        }
        return scores;
    }
    // old function to be deleted
    // public void printScores() {
    //     for (int i = 0; i < players.length; i++) {
    //         System.out.println(players[i].getName() + "'s wins: " + players[i].getWins());
    //     }
    // }

    public int getDeckSize() {
        return deck.size();
    }
}