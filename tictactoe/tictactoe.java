import java.util.Scanner;

public class TicTacToe {
    private boolean playerXturn;
    private char[][] board = new char[3][3];

    public static void main(String[] args) {
        System.out.println("test");
    }

    public TicTacToe() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = ' ';
            }
        }
    }
}
