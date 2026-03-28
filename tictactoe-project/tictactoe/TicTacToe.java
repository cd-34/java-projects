package tictactoe;
import java.util.Scanner;

public class TicTacToe {
    private Player player1;
    private Player player2;
    private static final int BOARD_SIZE = 3;
    private char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
    private static final char BLANK = '~';
    private int turnCount = 1;
    private Scanner scan;

    public TicTacToe(Player player1, Player player2, Scanner scan) {
        this.player1 = player1;
        this.player2 = player2;
        this.scan = scan;
    }

    public void init() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = BLANK;
            }
        }
        turnCount = 1;
    }

    public Player getCurrentPlayer() {
        return (turnCount % 2 != 0) ? player1 : player2;
    }

    @Override
    public String toString() {
        StringBuilder stringbuilder = new StringBuilder();
        // column labels (letters)
        stringbuilder.append("    ");
        for (int i = 0; i < BOARD_SIZE; i++) {
            stringbuilder.append((char) ('A' + i)).append("   ");
        }
        stringbuilder.append("\n");
        
        stringbuilder.append(makeTopBorder());
        
        // rows (numbers)
        for (int j = BOARD_SIZE; j >= 1; j--) {
            // prints the number on the left
            stringbuilder.append(j).append(" | ");
            // prints the board with the BLANK '~'
            for (int k = 0; k < BOARD_SIZE; k++) {
            stringbuilder.append(board[BOARD_SIZE - j][k]).append(" | ");
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
        for (int i = 0; i < BOARD_SIZE; i++) {
            border.append("----");
        }
        border.append("\n");
        return border.toString();
    }

    public void printTurn() {
        Player currentPlayer = getCurrentPlayer();
        System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")! Enter your move (e.g. A1, B2, C3):");
    }

    public void run() {
        boolean playing = true;
        while (playing) {
            init();
            Player winner = playGame();
            if (winner != null) {
                printWinner(winner);
            }
            playing = playAgain();
        }
    }

    public Player playGame() {
        while(true) {
            System.out.println(this);
            printTurn();
            
            int[] move = InputHandler.moveScanner(scan, BOARD_SIZE);
            int[] recentMove = boardChange(move);

            if (recentMove == null) {
                continue;
            }

            if (hasWon(recentMove) == true) {
                return getCurrentPlayer();
            }

            if (turnCount > BOARD_SIZE * BOARD_SIZE) {
                checkTie();
                return null;
            }
        }
    }

    public void checkTie() {
        System.out.println(this);
        System.out.println("Board has been filled, it's a tie!");
    }

    public int[] boardChange(int[] input) {
        if (board[input[0]][input[1]] == BLANK) {
            board[input[0]][input[1]] = getCurrentPlayer().getSymbol();
            return input;
        } else {
            System.out.println("That tile is already occupied!");
            return null;
        }
    }

    public boolean hasWon(int[] recentMove) {
        int row = recentMove[0];
        int col = recentMove[1];
        // creates an array for the current horizontal tiles 
        char[] horizontal = new char[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            horizontal[i] = board[row][i];
        }
        // creates an array for the current vertical tiles
        char[] vertical = new char[BOARD_SIZE];
        for (int j = 0; j < BOARD_SIZE; j++) {
            vertical[j] = board[j][col]; 
        }
        // creates an array for the current diagonal tiles
        char[] diag1 = new char[BOARD_SIZE];
        for (int k = 0; k < BOARD_SIZE; k++) {
            diag1[k] = board[k][k];
        }

        // creates an array for the current anti diagonal tiles
        char[] diag2 = new char[BOARD_SIZE]; 
        for (int l = 0; l < BOARD_SIZE; l++) {
            diag2[l] = board[l][BOARD_SIZE - 1 - l];
        }

        if (allElementsMatch(horizontal) 
            || allElementsMatch(vertical)
            || allElementsMatch(diag1)
            || allElementsMatch(diag2)) {
                return true;
            }
        turnCount++;
        return false;
    }

    public boolean allElementsMatch(char[] array) {
        // still need to check for blank because of the diagonals
        for (int i = 1; i < array.length; i++) {
            if (array[i] != array[i - 1] || array[i] == BLANK) {
                return false;
            }
        }
        return true;
    }

    public void printWinner(Player winner) {
        winner.incrementWins();

        System.out.println(winner.getName() + " wins!");
        System.out.println("Scores:");
        System.out.println(player1.getName() + ": " + player1.getWins() + " | " 
            + player2.getName() + ": " + player2.getWins());
    }

    public boolean playAgain() {
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