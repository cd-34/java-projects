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
            playing = inputHandler.playAgain(scan);
        }
    }

    public Player playGame() {
        GameState state = GameState.IN_PROGRESS;

        while (state == GameState.IN_PROGRESS) {
            System.out.println(tictactoe);
            printTurn();
            
            try {
                int[] move = inputHandler.moveScanner(scan);
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
        } else  {
            printTie();
            return null;
        }
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
}