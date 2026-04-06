package tictactoe;
import java.util.Scanner;

public class InputHandler {    
    private Scanner scan;
    private static int boardSize;
    
    public InputHandler(Scanner scan, int boardSize) {
        this.scan = scan;
        this.boardSize = boardSize;
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

    public static int[] moveScanner(Scanner scan) {
        while (true) {
            String moveString = scan.nextLine().toUpperCase().trim();

            if (moveString.length() != 2) {
                System.out.println("Please enter a valid input of two characters (e.g. A1, B2, C3):");
                continue;
            }

            char col = moveString.charAt(0);
            char row = moveString.charAt(1);
            char maxCol = (char) ('A' + boardSize - 1);
            char maxRow = (char) ('1' + boardSize - 1);

            if (col < 'A' || col > maxCol) {
                System.out.println("Column must be A and " + maxCol + ":");
                continue;
            }

            if (row < '1' || row > maxRow) {
                System.out.println("Row must be between 1 and " + maxRow + ":");
                continue;
            }

            // converts chess notation into [row][column]
            // for example A3 in boardSize 3
            // {3 - 3, A - A} = {0, 0}
            return new int[]{maxRow - row, col - 'A'};
        }
    }

    public static boolean playAgain(Scanner scan) {
        while (true) {
            System.out.println("Would you like to play again? Y / N");
            String newGame = scan.nextLine();
            
            if (newGame.toUpperCase().equals("Y")) {
                return true;
            } else if (newGame.toUpperCase().equals("N")) {
                return false;
            } else {
                System.out.println("Invalid input.");
            }
        }
    }
}