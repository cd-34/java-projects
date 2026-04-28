package chopsticks;
import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;
    private Player winner = null;
    private Scanner scan;
    private Chopsticks chopsticks;
    private MoveReader moveReader;

    public Game(Player player1, Player player2, MoveReader moveReader) {
        this.player1 = player1;
        this.player2 = player2;
        this.moveReader = moveReader;
        this.chopsticks = new Chopsticks(player1, player2);
    }

    // initializes the game and starts playGame() until there is a winner
    // once a winner is determined, asks to play again
    // if yes, re-initializes the game 
    // if no, ends the game
    public void run() {
        boolean playing = true;
        while (playing) {
            chopsticks.init();
            Player winner = playGame();
            playing = moveReader.playAgain(scan);
        }
    }

    // responsible for determining a winner
    public Player playGame() {
        GameState state = GameState.IN_PROGRESS;
        while (state == GameState.IN_PROGRESS) {
            System.out.println(chopsticks); // prints the "board"
            printTurn();

            Move move;

            // Loops until a valid move is made
            while(true) {
                move = moveReader.promptMove();
                if (chopsticks.isValidMove(move)) {
                    break;
                }
            }
            chopsticks.applyMove(move);
            if (!chopsticks.isGameOver()) {
                chopsticks.incrementTurnCount();
            } else {
                state = GameState.WON;
            }
        }
        
        Player currentPlayer = chopsticks.getCurrentPlayer();
        currentPlayer.incrementWins();
        printWinner();
        return chopsticks.getWinner(); 
    }

    public void printTurn() {
        Player currentPlayer = chopsticks.getCurrentPlayer();
        System.out.println(currentPlayer.getName() + "'s turn! Enter your move.");
        // maybe move this to a "rules" section that prints at the start of every game instead of every turn
        System.out.println("To attack, type the letter you want to attack with with the desired location (E.g. LR)");
        System.out.println("To split, type the numbers of how you'd like to split (E.g. 23)");
    }

    public void printWinner() {
        Player currentPlayer = chopsticks.getCurrentPlayer();
        System.out.println(chopsticks);
        System.out.println(currentPlayer.getName() + " wins!");
        System.out.println("Scores:");
        System.out.println(player1.getName() + ": " + player1.getWins() + " | " 
            + player2.getName() + ": " + player2.getWins());
    }
}