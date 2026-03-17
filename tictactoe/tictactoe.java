// note to self: to run the program, go into this folder with terminal and type
// java tictactoe.java

import java.util.Scanner;

public class TicTacToe {
    private static boolean playerXTurn = true; 
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
        System.out.println("-------------------");
        System.out.println(" " + board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println("----------");
        System.out.println(" " + board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println("----------");
        System.out.println(" " + board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
        if (playerXTurn) {
            System.out.println("Player X's turn! Enter your move.");
        } else {
            System.out.println("Player O's turn! Enter your move.");
        }
        
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

    public static void moveScanner() {
        Scanner sc = new Scanner(System.in);
        String moveString = sc.nextLine();
    
        if (moveString.isBlank()) {
            System.out.println("Please enter a character."); 
            moveScanner();
        } else if (moveString.length() > 1) {
            System.out.println("Please enter one character.");
            moveScanner();
        // this doesn't work rn, need to fix
        // } else if (moveString.toUpperCase().equals("X") || moveString.toUpperCase().equals("O")) {
        //     System.out.println("This space is already occupied!");
        //     moveScanner();
        } else if (Character.getNumericValue(moveString.charAt(0)) < 10 || Character.getNumericValue(moveString.charAt(0)) > 18) {
            System.out.println("Enter a legal letter from A to I");
            moveScanner();
        }

        // returns the character input needed to manipulate the board later
        // System.out.println(Character.toUpperCase(moveChar)); // testing only
        boardChange(Character.toUpperCase(moveString.charAt(0)));
    }

    // this is hideously long method
    // maybe a switch statement would look better?
    // tried to move printBoard() and moveScanner() into the helper method but didn't work
    public static void boardChange(char input) {
        if (input == 'A' && board[0][0] == 'A') {
            if (playerXTurn) {
                board[0][0] = 'X';
                playerXTurn = !playerXTurn;
            } else {
                board[0][0] = 'O';
                playerXTurn = !playerXTurn;
            }
            printBoard();
            moveScanner();
        } else if (input == 'B' && board[0][1] == 'B') {
            board[0][1] = boardChangeHelper();
            printBoard();
            moveScanner();
        } else if (input == 'C' && board[0][2] == 'C') {
            board[0][2] = boardChangeHelper();
            printBoard();
            moveScanner();
        } else if (input == 'D' && board[1][0] == 'D') {
            board[1][0] = boardChangeHelper();
            printBoard();
            moveScanner();
        } else if (input == 'E' && board[1][1] == 'E') {
            board[1][1] = boardChangeHelper();
            printBoard();
            moveScanner();
        } else if (input == 'F' && board[1][2] == 'F') {
            board[1][2] = boardChangeHelper();
            printBoard();
            moveScanner();
        } else if (input == 'G' && board[2][0] == 'G') {
            board[2][0] = boardChangeHelper();
            printBoard();
            moveScanner();
        } else if (input == 'H' && board[2][1] == 'H') {
            board[2][1] = boardChangeHelper();
            printBoard();
            moveScanner();
        } else if (input == 'I' && board[2][2] == 'I') {
            board[2][2] = boardChangeHelper();
            printBoard();
            moveScanner();
        } else {
            System.out.println("Already occupied! Pick another tile.");
            printBoard();
            moveScanner();
        }
    }

    public static char boardChangeHelper() {
        if (playerXTurn) {
            playerXTurn = !playerXTurn;
            return 'X';
        } else {
            playerXTurn = !playerXTurn;
            return 'O';
        }

    }

    public static boolean winCondition() {
        if (board[0][0] == board[0][1] && board[0][1] == board[0][2]) {
            System.out.println("Player " + board[0][0] + " wins!");
            return true;
        }
        else {
            System.out.println("");
            return false;
        }
    }
}

// TicTacToe newGame = new TicTacToe();