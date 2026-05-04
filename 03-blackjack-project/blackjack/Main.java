package blackjack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Scanner scan = new Scanner(System.in);
        Player player1 = new Player(ConsoleIO.askPlayerName(scan));
        System.out.println("Welcome " + player1.getName() + "!");

        int playerCount = ConsoleIO.askPlayerCount(scan);
        System.out.println(playerCount);
        scan.close();
    }
}