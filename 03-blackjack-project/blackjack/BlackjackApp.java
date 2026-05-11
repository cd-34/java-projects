package blackjack;
import java.util.Scanner;

public class BlackjackApp {
    private ConsoleIO consoleIO;

    public BlackjackApp(ConsoleIO consoleIO) {
        this.consoleIO = consoleIO;
    }

    // adds players and their names
    public void addPlayers(Scanner scan) {
        System.out.println("How many players will be playing?");
        int playerCount = consoleIO.askPlayerCount(); 

        // Create a new array that stores the players
        // Array is best because it's an immutable size
        Player[] players = new Player[playerCount];

        // Creates new player classes stored in players[] and sets their names
        for (int i = 0; i < playerCount; i++) {
            players[i] = new Player(consoleIO.askPlayerName(i)); 
            System.out.println("Welcome " + players[i].getName() + "!"); 
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

    // initializes a game and starts playGame() until there is a winner
    // once players are determined to have won/lost, asks to play again
    // if yes, reinitializes the game. otherwise ends the game
    public void run() {
        boolean playing = true;
        while (playing) {
            // blackjack.init();
            // playing = consoleIO.playAgain();
        }
    }

    // public void playGame();
}