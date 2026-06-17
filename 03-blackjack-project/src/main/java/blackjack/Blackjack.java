package blackjack;

import java.util.Map;
import java.util.LinkedHashMap;

// Library's purpose is only for running blackjack
public class Blackjack {
    private Deck deck;
    private Player[] players;
    private Dealer dealer;
    private Gamestate state = Gamestate.WAITING;
    private int currentPlayerIndex = 0;

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

    public Gamestate getState() {
        return state; // started as WAITING
    }

    public CurrentBoard initialDeal() {
        state = Gamestate.DEALING;
        dealer.addCard(deck.deal());

        for (int i = 0; i < players.length; i++) {
            players[i].addCard(deck.deal());
            players[i].addCard(deck.deal());
        }

        currentPlayerIndex = 0;
        state = Gamestate.PLAYER_TURNS;
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

    public void dealerTurn() {
        // dealer's playTurn goes until they stand or bust
        dealer.playTurn(deck); 
        // since dealer is now done, can swap game state to FINISHED
        state = Gamestate.FINISHED;
        // are these the kinds of comments Ousterhout would like? 
    }

    public void resetRound() {
        for (int i = 0; i < players.length; i++) {
            players[i].clearHand();
        }
        dealer.clearHand();
        deck = new Deck();
        deck.shuffle();
        state = Gamestate.WAITING;
    }

    // Thinking about it, I don't use Outcome for much now that Gamestate was refactoed
    // Outcome would probably still be useful for unit testing so it'll be left in
    public Map<String, Outcome> determineWinner() {
        // map used to produce one outcome per player
        Map<String, Outcome> results = new LinkedHashMap<>();
        int dealerValue = dealer.getHandValue();

        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            int playerValue = player.getHandValue();

            if (player.isBust()) {
                results.put(player.getName(), Outcome.PLAYER_LOSS);
            } else if (dealer.isBust() || playerValue > dealerValue) {
                player.incrementWins();
                results.put(player.getName(), Outcome.PLAYER_WIN);
            } else if (playerValue == dealerValue) {
                results.put(player.getName(), Outcome.TIE);
            } else {
                // happens when playerValue < dealerValue but neither bust
                results.put(player.getName(), Outcome.PLAYER_LOSS);
            }
        }
        state = Gamestate.FINISHED;
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
    // but this allows us to do it for each player in the array
    // issue with getters exposing internals and being shallow methods?
    // but want to hide currentPlayerIndex from BlackjackApp
    public int getPlayerCount() {
        return players.length;
    }

    public String getCurrentPlayerName() {
        return players[currentPlayerIndex].getName();
    }

    public int getCurrentPlayerHandValue() {
        return players[currentPlayerIndex].getHandValue();
    }

    public boolean isCurrentPlayerBust() {
        return players[currentPlayerIndex].isBust();
    }

    public void advanceToNextPlayer() {
        currentPlayerIndex++;
        if (currentPlayerIndex >= players.length) {
            state = Gamestate.DEALER_TURN;
        }
    }

    public Card hitCurrentPlayer() {
        Card card = deck.deal();
        players[currentPlayerIndex].addCard(card);
        return card;
    }

    public void standCurrentPlayer() {
        advanceToNextPlayer();
    }
}