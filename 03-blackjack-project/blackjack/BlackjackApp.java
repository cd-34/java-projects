package blackjack;
import java.util.Scanner;

public class BlackjackApp {
    private ConsoleIO consoleIO;

    public BlackjackApp(ConsoleIO consoleIO) {
        this.consoleIO = consoleIO;
    }

    public void addPlayers(Scanner scan) {
        System.out.println("How many players will be playing?");
        int playerCount = consoleIO.askPlayerCount(scan); 

        // Create a new array that stores the players
        Player[] players = new Player[playerCount];

        for (int i = 0; i < playerCount; i++) {
            players[i] = new Player(consoleIO.askPlayerName(scan, i)); // fix
            System.out.println("Welcome " + players[i].getName() + "!"); // fix
        }
    }
}