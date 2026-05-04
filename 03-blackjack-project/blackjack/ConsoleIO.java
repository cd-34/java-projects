package blackjack;
import java.util.Scanner;

public class ConsoleIO {
    private Scanner scan;

    public ConsoleIO(Scanner scan) {
        this.scan = scan;
    }

    public static String askPlayerName(Scanner scan) {
        while (true) {
            String inputName = scan.nextLine();

            if (inputName.isBlank()) {
                System.out.println("Invalid input - please enter your name.");
                continue;
            }
            return inputName;
        }
    }

    public static int askPlayerCount(Scanner scan) {
        while (true) {
            if (scan.hasNextInt()) {
                int playerCount = scan.nextInt();
                scan.nextLine();
                if (playerCount >= 1 && playerCount <= 4) {
                    return playerCount;
                } else {
                    System.out.println("This game only supports 1-4 players. Please enter a number again.");
                }
            } else {
                System.out.println("Please enter a number between 1-4.");
                scan.nextLine();
            }
        }
    }
}