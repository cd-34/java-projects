package chopsticks;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter player 1's name.");
        Player player1 = new Player(MoveReader.nameScanner(scan));
        System.out.println("Welcome " + player1.getName() + "!");

        System.out.println("Please enter player 2's name.");
        Player player2 = new Player(MoveReader.nameScanner(scan));
        System.out.println("Welcome " + player2.getName() + "!");

        Chopsticks chopsticks = new Chopsticks(player1, player2);
        MoveReader moveReader = new MoveReader(scan, chopsticks);

        // initialize game
        Game game = new Game(player1, player2, moveReader);
        game.run();

        scan.close();
    }
}