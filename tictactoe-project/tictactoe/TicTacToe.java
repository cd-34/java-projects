package tictactoe;
import java.util.Scanner;

public class TicTacToe {
    private Player player1;
    private Player player2;
    private Player winner = null;
    private final int boardSize;
    private char[][] board;
    private static final char BLANK = '~';
    private int turnCount = 1;
    private Scanner scan;

    public TicTacToe(Player player1, Player player2, int boardSize) {
        this.player1 = player1;
        this.player2 = player2;
        this.boardSize = boardSize;
        this.board = new char[boardSize][boardSize];
    }

    public void init() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = BLANK;
            }
        }
        turnCount = 1;
    }

    @Override
    public String toString() {
        StringBuilder stringbuilder = new StringBuilder();
        // column labels (letters)
        stringbuilder.append("    ");
        for (int i = 0; i < boardSize; i++) {
            stringbuilder.append((char) ('A' + i)).append("   ");
        }
        stringbuilder.append("\n");
        
        stringbuilder.append(makeTopBorder());
        
        // rows (numbers)
        for (int j = boardSize; j >= 1; j--) {
            // prints the number on the left
            stringbuilder.append(j).append(" | ");
            // prints the board with the BLANK '~'
            for (int k = 0; k < boardSize; k++) {
            stringbuilder.append(board[boardSize - j][k]).append(" | ");
            }
            stringbuilder.append("\n");
            stringbuilder.append(makeTopBorder());
        }
        return stringbuilder.toString();
    }

    public String makeTopBorder() {
        // creates top "------" border
        StringBuilder border = new StringBuilder();
        border.append("  ");
        for (int i = 0; i < boardSize; i++) {
            border.append("----");
        }
        border.append("\n");
        return border.toString();
    }

    public GameState place(int[] coords) throws InvalidMoveException {
        if (coords[0] < 0 || coords[1] >= boardSize
        || coords[1] < 0 || coords[1] >= boardSize) {
            throw new InvalidMoveException("coords out of bounds");
        }

        if (board[coords[0]][coords[1]] != BLANK) {
            throw new InvalidMoveException("Tile already occupied");
        }

        board[coords[0]][coords[1]] = getCurrentPlayer().getSymbol();

        if (hasWon(coords)) {
            winner = getCurrentPlayer();
            return GameState.WON;
        }

        if (turnCount >= boardSize * boardSize) {
            return GameState.TIED;
        }

        turnCount++;
        return GameState.IN_PROGRESS;
    }

    private boolean hasWon(int[] recentMove) {
        int row = recentMove[0];
        int col = recentMove[1];
        // creates an array for the current horizontal tiles 
        char[] horizontal = new char[boardSize];
        for (int i = 0; i < boardSize; i++) {
            horizontal[i] = board[row][i];
        }
        // creates an array for the current vertical tiles
        char[] vertical = new char[boardSize];
        for (int j = 0; j < boardSize; j++) {
            vertical[j] = board[j][col]; 
        }
        // creates an array for the current diagonal tiles
        char[] diag1 = new char[boardSize];
        for (int k = 0; k < boardSize; k++) {
            diag1[k] = board[k][k];
        }

        // creates an array for the current anti diagonal tiles
        char[] diag2 = new char[boardSize]; 
        for (int l = 0; l < boardSize; l++) {
            diag2[l] = board[l][boardSize - 1 - l];
        }

        if (allElementsMatch(horizontal) 
            || allElementsMatch(vertical)
            || allElementsMatch(diag1)
            || allElementsMatch(diag2)) {
                return true;
            }
        return false;
    }

    private boolean allElementsMatch(char[] array) {
        // still need to check for blank because of the diagonals
        for (int i = 1; i < array.length; i++) {
            if (array[i] != array[i - 1] || array[i] == BLANK) {
                return false;
            }
        }
        return true;
    }

    public Player getCurrentPlayer() {
        return (turnCount % 2 != 0) ? player1 : player2;
    }

    public Player getWinner() {
        return winner;
    }
}