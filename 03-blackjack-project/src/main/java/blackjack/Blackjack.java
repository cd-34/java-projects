package blackjack;

import java.util.Map;
import java.util.LinkedHashMap;

// Library's purpose is only for running blackjack
public class Blackjack {
    private Deck deck;
    private Player[] players;
    private Dealer dealer;

    public Blackjack() {
        this.deck = new Deck();
        this.deck.shuffle();
        this.dealer = new Dealer();
    }

    // "record" keyword best for just passing around data
    // in this case, trying to pass around data on dealer's hand and players' hands
    public record CurrentBoard(String dealerHand, Map<String, String> playerHands) {}

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public CurrentBoard initialDeal() {
        dealer.addCard(deck.deal());

        for (int i = 0; i < players.length; i++) {
            players[i].addCard(deck.deal());
            players[i].addCard(deck.deal());
        }
        return dealResult();
    }

    // returns a dealer's hand and the player's <name, hand> as a map
    public CurrentBoard dealResult() {
        Map<String, String> playerHands = new LinkedHashMap<>();
        for (int i = 0; i < players.length; i++) {
            playerHands.put(players[i].getName(), players[i].getHand().toString());
        }
        return new CurrentBoard(dealer.getHand().toString(), playerHands);
    }

    // shallow method
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

    public Map<String, Gamestate> determineWinner() {
        // map used to produce one gamestate per player
        Map<String, Gamestate> results = new LinkedHashMap<>();
        int dealerValue = dealer.getHandValue();

        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            int playerValue = player.getHandValue();

            if (player.isBust()) {
                results.put(player.getName(), Gamestate.PLAYER_LOSS);
            } else if (dealer.isBust() || playerValue > dealerValue) {
                player.incrementWins();
                results.put(player.getName(), Gamestate.PLAYER_WIN);
            } else if (playerValue == dealerValue) {
                results.put(player.getName(), Gamestate.TIE);
            } else {
                // happens when playerValue < dealerValue but neither bust
                results.put(player.getName(), Gamestate.PLAYER_LOSS);
            }
        }
        return results;
    }

    // only used once in blackjackapp under run()
    public Map<String, Integer> getScores() {
        Map<String, Integer> scores = new LinkedHashMap<>();
        for (int i = 0; i < players.length; i++) {
            scores.put(players[i].getName(), players[i].getWins());
        }
        return scores;
    }

    public int getDeckSize() {
        return deck.size();
    }

    // consequence of removing player information from blackjackapp
    // duplicate code, need to fix later
    public int getPlayerCount() {
        return players.length;
    }

    public String getPlayerName(int i) {
        return players[i].getName();
    }

    public int getPlayerHandValue(int i) {
        return players[i].getHandValue();
    }

    public boolean isPlayerBust(int i) {
        return players[i].isBust();
    }
}