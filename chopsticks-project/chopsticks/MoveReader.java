package chopsticks;
import java.util.Scanner;

public class MoveReader {
    private Scanner scan;
    private Chopsticks chopsticks;

    public MoveReader(Scanner scan, Chopsticks chopsticks) {
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

    public Move promptMove() {
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
            // if (move == null) {
            //     continue;
            // }
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

    public Side parseSide(char c) {
        if (c == 'L') {
            return Side.LEFT;
        }
        if (c == 'R') {
            return Side.RIGHT;
        }
        return null;
    }

    private Move parseAttack(String move) {
        Side start = parseSide(move.charAt(0));
        Side end = parseSide(move.charAt(1));
        return Move.attack(start, end);
    }

    private Move parseSplit(String move) {
        return Move.split(move.charAt(0) - '0', move.charAt(1) - '0');
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
}