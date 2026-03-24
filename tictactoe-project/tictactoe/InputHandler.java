package tictactoe;
import java.util.Scanner;

public class InputHandler {    
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

    public static char[] moveScanner(Scanner scan) {
        while (true) {
            String moveString = scan.nextLine().toUpperCase().trim();

            if (moveString.length() != 2) {
                System.out.println("Please enter a valid input of two characters (e.g. A1, B2, C3):");
                continue;
            }

            char col = moveString.charAt(0);
            char row = moveString.charAt(1);

            if (col < 'A' || col > 'C') {
                System.out.println("Column must be A, B, or C:");
                continue;
            }

            if (row < '1' || row > '3') {
                System.out.println("Row must be be 1, 2, or 3:");
                continue;
            }

            return new char[]{col, row};
        }
    }
}