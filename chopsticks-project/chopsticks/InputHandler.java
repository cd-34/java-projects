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

    public int moveScanner(Scanner scan) {
        while (true) {
            String move = scan.nextLine().toUpperCase().trim();
            
            if (move.length() != 2) {
                System.out.println("Please enter a valid input of two characters (E.g. LR or 23)");
                continue;
            }

            if (isAttack(move)) {
                return parseAttack(move);
            } else if (isSplit(move)) {
                return parseSplit(move);
            } else {
                System.out.println("Invalid move format.");
            }
        }
    }

    private boolean isAttack(String move) {
        return (move.charAt(0) == 'L' || move.charAt(0) == 'R') 
            && (move.charAt(1) == 'L' || move.charAt(1) == 'R');
    }

    private boolean isSplit(String move) {
        return Character.isDigit(move.charAt(0)) 
            && Character.isDigit(move.charAt(1));
    }

    private int parseAttack(String move) {
        char start = move.charAt(0);
        char end = move.charAt(1);

        Player current = chopsticks.getCurrentPlayer();
        Player opponent = chopsticks.getOpposingPlayer();

        int attackValue = (start == 'L') ? current.getLeftHand() : current.getRightHand();
        int targetValue = (end == 'L') ? opponent.getLeftHand() : opponent.getRightHand();

        return attackValue + targetValue;
    }

    private int parseSplit(String move) {
        int left = move.charAt(0) - '0';
        int right = move.charAt(1) - '0';

        Player current = chopsticks.getCurrentPlayer();
        int currentTotal = current.getLeftHand() + current.getRightHand();

        // validating range
        if (left < 1 || left > 4 || right < 1 || right > 4) {
            System.out.println("Both hands must be between 1 and 4");
            return -1;
        }

        // validating total
        if (left + right != currentTotal) {
            System.out.println("Split must preserve total.");
            return -1;
        }

        // validating not identical
        if ((left == current.getLeftHand() && right == current.getRightHand())
            || (left == current.getRightHand() && right == current.getLeftHand())) {
            System.out.println("Split is identical to current position");
            return -1;
        }
        
        return left + right; // temporary need to fix to properly assign hands
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