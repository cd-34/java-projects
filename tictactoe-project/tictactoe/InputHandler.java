package tictactoe;
import java.util.Scanner;

public class InputHandler {    
    private Scanner scan;
    private static int BOARD_SIZE;
    
    public InputHandler(Scanner scan, int BOARD_SIZE) {
        this.scan = scan;
        this.BOARD_SIZE = BOARD_SIZE;
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

    public int[] moveScanner() {
        while (true) {
            String moveString = this.scan.nextLine().toUpperCase().trim();

            if (moveString.length() != 2) {
                System.out.println("Please enter a valid input of two characters (e.g. A1, B2, C3):");
                continue;
            }

            char col = moveString.charAt(0);
            char row = moveString.charAt(1);
            char maxCol = (char) ('A' + BOARD_SIZE - 1);
            char maxRow = (char) ('1' + BOARD_SIZE - 1);

            if (col < 'A' || col > maxCol) {
                System.out.println("Column must be A and " + maxCol + ":");
                continue;
            }

            if (row < '1' || row > maxRow) {
                System.out.println("Row must be between 1 and " + maxRow + ":");
                continue;
            }

            // converts chess notation into [row][column]
            // for example A3 in BOARD_SIZE 3
            // {3 - 3, A - A} = {0, 0}
            return new int[]{maxRow - row, col - 'A'};
        }
    }
}