package chopsticks;
import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;
    private Scanner scan;

    public Game(Player player1, Player player2, Scanner scan) {
        this.player1 = player1;
        this.player2 = player2;
        this.scan = scan;
    }

    public void run() {
        boolean playing = true;
        while (playing) {
            // need to initialize the game with chopsticks.init();
            // need to play the game 
            // 
            // playing = inputHandler.playAgain(scan);
        }
    }

    public Player playGame() {
        GameState state = GameState.IN_PROGRESS;
    }
}