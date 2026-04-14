package chopsticks;
import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;
    private Player winner = null;
    private Scanner scan;
    private Chopsticks chopsticks;
    private InputHandler inputHandler;

    public Game(Player player1, Player player2, Scanner scan) {
        this.player1 = player1;
        this.player2 = player2;
        this.scan = scan;
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
            // need to create playAgain to return true/false based on scanner
            playing = inputHandler.playAgain(scan);
        }
    }

    // responsible for determining a winner
    public Player playGame() {
        GameState state = GameState.IN_PROGRESS;
        while (state == GameState.IN_PROGRESS) {
            state = GameState.WON; // temporary
        }

        // should return winner 
        return null; // temporary
    }
}