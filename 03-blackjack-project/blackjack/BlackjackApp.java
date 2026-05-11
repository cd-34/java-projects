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
        int playerCount = consoleIO.askPlayerCount(scan); 

        // Create a new array that stores the players
        // Array is best because it's an immutable size
        Player[] players = new Player[playerCount];

        // Creates new player classes stored in players[] and sets their names
        for (int i = 0; i < playerCount; i++) {
            players[i] = new Player(consoleIO.askPlayerName(scan, i)); 
            System.out.println("Welcome " + players[i].getName() + "!"); 
        }

        // temporarily add two cards to player1's hand then print them out to test
        Deck deck = new Deck();
        deck.shuffle();
        players[0].addCard(deck.deal());
        players[0].addCard(deck.deal());
        System.out.println(players[0].getName() + "'s hand:");
        System.out.println(players[0].getHand());
    }

    // initializes a game based on number of players
    // public void run() {

    // }
}