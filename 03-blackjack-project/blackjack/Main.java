package blackjack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Scanner scan = new Scanner(System.in);

        System.out.println("How many players will be playing?");
        int playerCount = ConsoleIO.askPlayerCount(scan);

        Player[] players = new Player[playerCount];

        for (int i = 0; i < playerCount; i++) {
            System.out.println("Please enter player " + (i + 1) + "'s name.");
            players[i] = new Player(ConsoleIO.askPlayerName(scan)); // fix
            System.out.println("Welcome " + players[i].getName() + "!"); // fix
        }        
        scan.close();
    }
}