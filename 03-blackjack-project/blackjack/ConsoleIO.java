package blackjack;
import java.util.Scanner;

public class ConsoleIO {
    private Scanner scan;

    public ConsoleIO(Scanner scan) {
        this.scan = scan;
    }

    public String askPlayerName(int playerNumber) {
        while (true) {
            System.out.println("Please enter player " + (playerNumber + 1) + "'s name.");
            String inputName = scan.nextLine();

            if (inputName.isBlank()) {
                System.out.println("Invalid input - please enter your name.");
                continue;
            }
            return inputName;
        }
    }

    public int askPlayerCount() {
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

    public boolean playAgain() {
        while (true) {
            System.out.println("Would you like to play again? Y / N");
            String response = scan.nextLine();
            if (response.toUpperCase().equals("Y")) {
                return true;
            } else if (response.toUpperCase().equals("N")) {
                return false;
            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    // change to return an enum
    public Action askHitOrStand() {
        while (true) {
            System.out.println("Hit or stand? H / S");
            String input = scan.nextLine().trim().toUpperCase();
            if (input.equals("H")) {
                return Action.HIT;
            }
            if (input.equals("S")) {
                return Action.STAND;
            }
            System.out.println("Invalid input — please enter 'h' or 's'.");
        }
    }
}