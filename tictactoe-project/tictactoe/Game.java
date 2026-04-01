package tictactoe;
import java.util.Scanner;

public class Game {
    private TicTacToe tictactoe;
    private InputHandler inputHandler;
    private Player player1;
    private Player player2;
    private Scanner scan;

    public Game(Player player1, Player player2, InputHandler inputHandler, Scanner scan) {
        this.player1 = player1;
        this.player2 = player2;
        this.inputHandler = inputHandler;
        this.scan = scan;
        this.tictactoe = new TicTacToe(player1, player2);
    }

    public void run() {
        boolean playing = true;
        while (playing) {
            tictactoe.init();
            Player winner = playGame();
            playing = playAgain();
        }
    }

    public Player playGame() {
        GameState state = GameState.IN_PROGRESS;

        while (state == GameState.IN_PROGRESS) {
            System.out.println(tictactoe);
            printTurn();
            
            try {
                int[] move = inputHandler.moveScanner();
                state = tictactoe.place(move);
            } catch (InvalidMoveException e) {
                System.out.println("Invalid move.");
                continue;
            }
        }

        // game no longer in process: win or tie
        System.out.println(tictactoe);

        if (state == GameState.WON) {
            printWinner();
            return tictactoe.getWinner();
        } else { 
            printTie();
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

    public void printWinner() {
        tictactoe.getWinner().incrementWins();

        System.out.println(tictactoe.getWinner().getName() + " wins!");
        System.out.println("Scores:");
        System.out.println(player1.getName() + ": " + player1.getWins() + " | " 
            + player2.getName() + ": " + player2.getWins());
    }

    public void printTurn() {
        Player currentPlayer = tictactoe.getCurrentPlayer();
        System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")! Enter your move (e.g. A1, B2, C3):");
    }

    public void printTie() {
        System.out.println("Board has been filled, it's a tie!");
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