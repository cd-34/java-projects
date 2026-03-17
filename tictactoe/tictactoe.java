// note to self: to run the program, go into this folder with terminal and type
// java tictactoe.java

import java.util.Scanner;

public class TicTacToe {
    private static char playerTurn = 'X';
    private static char[][] board = new char[3][3];

    public TicTacToe() {
        
    }    

    public static void initGame() {
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


    public static void main() {
        initGame();
        printBoard();
        moveScanner();
    }

    public static void moveScanner() {
        Scanner move = new Scanner(System.in);
        String input = move.nextLine();
        System.out.println(input);
    }

}

// TicTacToe newGame = new TicTacToe();