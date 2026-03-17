// note to self: to run the program, go into this folder with terminal and type
// java tictactoe.java

import java.util.Scanner;

public class TicTacToe {
    private static char playerTurn = 'X';
    private static char[][] board = new char[3][3];

    public TicTacToe() {
        
    }    

    public static void initGame() {
        // this method will initialize char[][] board to ABCEDFGHI
        String boardLetters = "ABCDEFGHI";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = boardLetters.charAt(i * 3 + j);
            }
        }
    }

    public static void printBoard() {
        System.out.println(" " + board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println("----------");
        System.out.println(" " + board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println("----------");
        System.out.println(" " + board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
        System.out.println("Player " + playerTurn + "'s turn! Enter your move.");
    }

    // first time using static void main() method, not sure if this is the proper implementation
    // because this is static, I feel like I'm forced make my other methods static as well
    // otherwise the compiler will complain because a non-static method is being called within this static main() method
    // what's the solution?
    public static void main() {
        initGame();
        printBoard();
        moveScanner();
    }

    public static char moveScanner() {
        Scanner sc = new Scanner(System.in);
        String moveString = sc.nextLine();
        char moveChar = moveString.charAt(0);
    
        if (moveString.length() == 0) {
            // produces compiler errors but continues to run fine
            // if I tried to throw new error, it would exit the if statement and not recurse
            // TODO find if there's a way to throw an exception but continue the if statement
            System.out.println("Please enter a character."); 
            moveScanner();
        } else if (moveString.length() > 1) {
            System.out.println("Please enter one character.");
            moveScanner();
        } else if (moveString.toUpperCase().equals("X") || moveString.toUpperCase().equals("O")) {
            System.out.println("This space is already occupied!");
            moveScanner();
        } else if (Character.getNumericValue(moveChar) < 10 || Character.getNumericValue(moveChar) > 18) {
            System.out.println("Enter a legal space from A to I");
            moveScanner();
        }

        char moveCharTemp = moveString.charAt(0);
        // returns the character input needed to manipulate the board later
        return Character.toUpperCase(moveCharTemp);
    }
}

// TicTacToe newGame = new TicTacToe();