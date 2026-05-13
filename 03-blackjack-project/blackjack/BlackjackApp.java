package blackjack;
import java.util.Scanner;

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
            blackjack.init(players);
            // after init, players should be asked one by one for their move
            playRound();
            playing = consoleIO.playAgain();
        }
    }

    public void playRound() {
        for (int i = 0; i < players.length; i++) {
            while (!players[i].isPlayerBust()) {
                System.out.println(players[i].getName() + "'s turn! Your current hand value: " + players[i].getHandValue());
                Action action = consoleIO.askHitOrStand();
                if (action == Action.HIT) {
                    blackjack.dealCardToPlayer(i);
                } else if (action == Action.STAND) {
                    break;
                } else {
                    throw new IllegalArgumentException("Invalid action.");
                }
            }
            if (players[i].isPlayerBust()) {
                System.out.println(players[i].getName() + " busts with a total of " + players[i].getHandValue());
            }
        }
    }
}