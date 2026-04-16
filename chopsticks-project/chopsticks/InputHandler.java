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

    public Move moveScanner() {
        while (true) {
            String moveString = scan.nextLine().toUpperCase().trim();
            
            if (moveString.length() != 2) {
                System.out.println("Please enter a valid input of two characters (E.g. LR or 23)");
                continue;
            }

            Move move;

            if (isAttack(moveString)) {
                move = parseAttack(moveString);
            } else if (isSplit(moveString)) {
                move = parseSplit(moveString);
            } else {
                System.out.println("Invalid move format.");
                continue;
            }
            if (move == null) {
                continue;
            }
            return move;
        }
    }

    // these four methods below can be private right? 
    // since they're only used in moveScanner for validation
    // but better to keep the others public?
    private boolean isAttack(String move) {
        return (move.charAt(0) == 'L' || move.charAt(0) == 'R') 
            && (move.charAt(1) == 'L' || move.charAt(1) == 'R');
    }

    private boolean isSplit(String move) {
        return Character.isDigit(move.charAt(0)) 
            && Character.isDigit(move.charAt(1));
    }

    private Move parseAttack(String move) {
        char start = move.charAt(0);
        char end = move.charAt(1);

        Player current = chopsticks.getCurrentPlayer();
        Player opponent = chopsticks.getOpposingPlayer();

        int attackValue = (start == 'L') ? current.getLeftHand() : current.getRightHand();
        int targetValue = (end == 'L') ? opponent.getLeftHand() : opponent.getRightHand();
        
        // this only returns the start and ending hand
        // need to add them up in Game
        return Move.attack(start, end);
    }

    private Move parseSplit(String move) {
        int left = move.charAt(0) - '0';
        int right = move.charAt(1) - '0';

        Player current = chopsticks.getCurrentPlayer();
        int currentTotal = current.getLeftHand() + current.getRightHand();

        // validating range
        if (left < 1 || left > 4 || right < 1 || right > 4) {
            System.out.println("Both hands must be between 1 and 4");
            return null;
        }

        // validating total
        if (left + right != currentTotal) {
            System.out.println("Split must preserve total.");
            return null;
        }

        // validating not identical
        if ((left == current.getLeftHand() && right == current.getRightHand())
            || (left == current.getRightHand() && right == current.getLeftHand())) {
            System.out.println("Split is identical to current position");
            return null;
        }
        
        // this only returns what your hands are after splitting
        // need to actually change it in game
        return Move.split(left, right);
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