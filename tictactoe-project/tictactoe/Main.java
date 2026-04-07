package tictactoe;
import java.util.Scanner;

public class Main {
    private static final int boardSize = 3;
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        InputHandler inputHandler = new InputHandler(scan, boardSize);

        System.out.println("Please enter player 1's name.");
        Player player1 = new Player(inputHandler.nameScanner(scan), 'X');
        System.out.println("Welcome " + player1.getName() + "!");

        System.out.println("Please enter player 2's name.");
        Player player2 = new Player(inputHandler.nameScanner(scan), 'O');
        System.out.println("Welcome " + player2.getName() + "!");

        Game game = new Game(player1, player2, inputHandler, scan, boardSize);
        game.run();

        scan.close();
    }
}