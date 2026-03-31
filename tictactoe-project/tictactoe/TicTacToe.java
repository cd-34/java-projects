package tictactoe;
import java.util.Scanner;

public class TicTacToe {
    private Player player1;
    private Player player2;
    private Player winner = null;
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
            playing = playAgain();
        }
    }

    public Player playGame() {
        GameState state = GameState.IN_PROGRESS;

        while(state == GameState.IN_PROGRESS) {
            System.out.println(this);
            printTurn();
            
            try {
                int[] move = InputHandler.moveScanner(scan, BOARD_SIZE);
                state = place(move);
            } catch (InvalidMoveException e) {
                System.out.println("error shouldn't be reached");
                continue;
            }
        }

        // game no longer in process: win or tie
        System.out.println(this);

        if (state == GameState.WON) {
            return getWinner();
        } else  {
            return tiedGame();
        }
    }

    public Player tiedGame() {
        System.out.println("Board has been filled, it's a tie!");
        return null;
    }

    public GameState place(int[] coords) throws InvalidMoveException {
        if (coords[0] < 0 || coords[1] >= BOARD_SIZE
        || coords[1] < 0 || coords[1] >= BOARD_SIZE) {
            throw new InvalidMoveException("coords out of bounds");
        }

        if (board[coords[0]][coords[1]] != BLANK) {
            throw new InvalidMoveException("Tile already occupied");
        }

        board[coords[0]][coords[1]] = getCurrentPlayer().getSymbol();
        System.out.println(turnCount);

        if (hasWon(coords)) {
            winner = getCurrentPlayer();
            return GameState.WON;
        }

        if (turnCount >= BOARD_SIZE * BOARD_SIZE) {
            return GameState.TIED;
        }

        turnCount++;
        return GameState.IN_PROGRESS;
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

    public Player getWinner() {
        winner.incrementWins();

        System.out.println(winner.getName() + " wins!");
        System.out.println("Scores:");
        System.out.println(player1.getName() + ": " + player1.getWins() + " | " 
            + player2.getName() + ": " + player2.getWins());
        return getCurrentPlayer();
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