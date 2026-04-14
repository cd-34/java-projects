package chopsticks;
import java.util.Scanner;

public class InputHandler {
    private Scanner scan;
    private Chopsticks chopsticks;

    public InputHandler(Scanner scan) {
        this.scan = scan;
    }

    public static String nameScanner(Scanner scan) {
        while (true) {
            String inputName = scan.nextLine();

            if (inputName.isBlank()) {
                System.out.println("Invalid input - please enter your name.");
                continue;
            }
            return inputName;
        }
    }

    // maybe I should split this into two methods
    // a scanner for attacks and a scanner for splits instead of combining them
    public int moveScanner(Scanner scan) {
        while (true) {
            String moveString = scan.nextLine().toUpperCase().trim();
            
            if (moveString.length() != 2) {
                System.out.println("Please enter a valid input of two characters (E.g. LR or 23)");
                continue;
            }

            char one = moveString.charAt(0);
            char two = moveString.charAt(1);
            
            // Procedure for attacks
            if (one == 'L' || one == 'R') {
                if (two != 'L' || two != 'R') {
                    System.out.println("Please enter a legal attack (E.g. LL, LR, RL, RR):");
                    continue;
                }
            }

            // Procedure for splitting
            Player currentPlayer = chopsticks.getCurrentPlayer();
            if (one > 1 && one < 5) {
                // new split has one hand dead or the numbers are beyond the limits
                if (two < 1 || two >= 5) {
                    System.out.println("Please enter a legal split (both hands between 1 and 4):");
                    continue;   
                }
                
                // new split doesn't add up to the same amount
                if (one + two != currentPlayer.getLeftHand() + currentPlayer.getRightHand()) {
                    System.out.println("The split you've entered does not add to your current total.");
                    continue;
                }
                // new split is the same as the current split
                if (one == currentPlayer.getRightHand() && two == currentPlayer.getLeftHand()
                    || one == currentPlayer.getLeftHand() && two == currentPlayer.getRightHand()) {
                    System.out.println("The split you've entered is identical to your previous split.");
                    continue;
                }
            }

            return 0; // temporary
        }
    }

    public static boolean playAgain(Scanner scan) {
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
}