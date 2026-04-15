package chopsticks;
import java.util.Scanner;

public class InputHandler {
    private Scanner scan;
    private Chopsticks chopsticks;

    public InputHandler(Scanner scan, Chopsticks chopsticks) {
        this.scan = scan;
        this.chopsticks = chopsticks;
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
            Player currentPlayer = chopsticks.getCurrentPlayer();
            Player opposingPlayer = chopsticks.getOpposingPlayer();
            
            // Procedure for attacks
            if (one == 'L' || one == 'R') {
                if (two != 'L' && two != 'R') {
                    System.out.println("Please enter a legal attack (E.g. LL, LR, RL, RR):");
                    continue;
                }
                // need to figure out how to determine what to add to what
                // maybe ternary statement here is better? 
                if (one == 'L') {
                    if (two == 'L') {
                        return currentPlayer.getLeftHand() + opposingPlayer.getLeftHand();
                    } else {
                        return currentPlayer.getLeftHand() + opposingPlayer.getRightHand();
                    }
                } 
                if (one == 'R') {
                    if (two == 'L') {
                        return currentPlayer.getRightHand() + opposingPlayer.getLeftHand();
                    } else {
                        return currentPlayer.getRightHand() + opposingPlayer.getRightHand();
                    }
                }
                
            }

            // Procedure for splitting
            int digitOne = one - '0';
            int digitTwo = two - '0';

            if (digitOne >= 1 && one <= 4) {
                // new split has one hand dead or the numbers are beyond the limits
                if (digitTwo < 1 || digitTwo >= 5) {
                    System.out.println("Please enter a legal split (both hands between 1 and 4):");
                    continue;   
                }
                
                // new split doesn't add up to the same amount
                if (digitOne + digitTwo != currentPlayer.getLeftHand() + currentPlayer.getRightHand()) {
                    System.out.println("The split you've entered does not add to your current total.");
                    continue;
                }
                // new split is the same as the current split
                if (digitOne == currentPlayer.getRightHand() && digitTwo == currentPlayer.getLeftHand()
                    || digitOne == currentPlayer.getLeftHand() && digitTwo == currentPlayer.getRightHand()) {
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