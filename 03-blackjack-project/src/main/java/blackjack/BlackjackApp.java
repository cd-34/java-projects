package blackjack;
import java.util.Map;
import java.util.Scanner;

import blackjack.Blackjack.CurrentBoard;

public class BlackjackApp {
    private ConsoleIO consoleIO;
    private Blackjack blackjack;

    public BlackjackApp(ConsoleIO consoleIO) {
        this.consoleIO = consoleIO;
        this.blackjack = new Blackjack();
    }

    // adds players and their names
    public void addPlayers(Scanner scan) {
        System.out.println("How many players will be playing?");
        int playerCount = consoleIO.askPlayerCount(); 
        Builder builder = new Builder();

        // Creates new player classes stored in players[] and sets their names
        for (int i = 0; i < playerCount; i++) {
            String name = consoleIO.askPlayerName(i);
            builder.join(name);
            System.out.println("Welcome " + name + "!"); 
        }
        this.blackjack = builder.build();
    }

    // initializes a game and starts playGame() until there is a winner
    // once players are determined to have won/lost, asks to play again
    // if yes, reinitializes the game. otherwise ends the game
    public void run() {
        boolean playing = true;
        while (playing) {
            CurrentBoard result = blackjack.initialDeal();
            System.out.println("Dealer's hand:\n" + result.dealerHand());
            for (Map.Entry<String, String> entry : result.playerHands().entrySet()) {
                System.out.println(entry.getKey() + "'s hand:\n" + entry.getValue());
            }
            // after initial deal, players should be asked one by one for their move
            playRound();
            blackjack.dealerTurn();
            // determine winners and print scores
            Map<String, Gamestate> results = blackjack.determineWinner();
            for (Map.Entry<String, Gamestate> entry: results.entrySet()) {
                String name = entry.getKey();
                Gamestate state = entry.getValue();
                
                switch (state) {
                    case PLAYER_WIN -> System.out.println(name + " wins!");
                    case PLAYER_LOSS -> System.out.println(name + " loses.");
                    case TIE -> System.out.println(name + " ties.");
                    case IN_PROGRESS -> throw new UnsupportedOperationException("Unimplemented case: " + state);
                }
            }
            
            // print out total wins for each player
            for (Map.Entry<String, Integer> entry: blackjack.getScores().entrySet()) {
            System.out.println(entry.getKey() + "'s wins: " + entry.getValue());
            }
            // ask to play again, if true then reset hands 
            playing = consoleIO.playAgain();
            blackjack.resetRound();
        }
    }

    // need to do player turn and represent more elegantly than index
    // blackjackapp shouldn't deal with states of playerbust etc
        // blackjack knows turn, player, what phase we're in
        // blackjackApp just acts based on that info 
    // model as a FSM (finite state machine)
    public void playRound() {
        for (int i = 0; i < blackjack.getPlayerCount(); i++) {
            while (!blackjack.isPlayerBust(i)) {
                System.out.println(blackjack.getPlayerName(i) + "'s turn! Your current hand value: " + blackjack.getPlayerHandValue(i));
                Action action = consoleIO.askHitOrStand();
                if (action == Action.HIT) {
                    System.out.println(blackjack.dealCardToPlayer(i));
                } else if (action == Action.STAND) {
                    break;
                } else {
                    throw new IllegalArgumentException("Invalid action.");
                }
            }
            if (blackjack.isPlayerBust(i)) {
                System.out.println(blackjack.getPlayerName(i) + " busts with a total of " + blackjack.getPlayerHandValue(i));
            }
        }
    }
}