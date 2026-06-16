package blackjack;
import java.util.Map;
import java.util.Scanner;

import blackjack.Blackjack.InitialDeal;

public class BlackjackApp {
    private ConsoleIO consoleIO;
    private Blackjack blackjack;
    private Player[] players;

    public BlackjackApp(ConsoleIO consoleIO) {
        this.consoleIO = consoleIO;
        this.blackjack = new Blackjack();
    }

    // adds players and their names
    public void addPlayers(Scanner scan) {
        System.out.println("How many players will be playing?");
        int playerCount = consoleIO.askPlayerCount(); 

        // Create a new array that stores the players
        // Array is best because it's an immutable size
        this.players = new Player[playerCount];

        // Creates new player classes stored in players[] and sets their names
        for (int i = 0; i < playerCount; i++) {
            players[i] = new Player(consoleIO.askPlayerName(i)); 
            System.out.println("Welcome " + players[i].getName() + "!"); 
        }
    }

    // initializes a game and starts playGame() until there is a winner
    // once players are determined to have won/lost, asks to play again
    // if yes, reinitializes the game. otherwise ends the game
    public void run() {
        boolean playing = true;
        while (playing) {
            // init deals one card to the dealer and two cards to each player
            InitialDeal result = blackjack.init(players);
            System.out.println("Dealer's hand:\n" + result.dealerHand());
            for (Map.Entry<String, String> entry : result.playerHands().entrySet()) {
                System.out.println(entry.getKey() + "'s hand:\n" + entry.getValue());
            }
            // after init, players should be asked one by one for their move
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

    public void playRound() {
        for (int i = 0; i < players.length; i++) {
            // can maybe put isBlackjack() here 
            while (!players[i].isBust()) {
                System.out.println(players[i].getName() + "'s turn! Your current hand value: " + players[i].getHandValue());
                Action action = consoleIO.askHitOrStand();
                if (action == Action.HIT) {
                    System.out.println(blackjack.dealCardToPlayer(i));
                } else if (action == Action.STAND) {
                    break;
                } else {
                    throw new IllegalArgumentException("Invalid action.");
                }
            }
            if (players[i].isBust()) {
                System.out.println(players[i].getName() + " busts with a total of " + players[i].getHandValue());
            }
        }
    }
}