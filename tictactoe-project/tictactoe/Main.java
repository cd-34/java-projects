package tictactoe;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter player 1's name.");
        Player player1 = new Player(InputHandler.nameScanner(scan), 'X');
        System.out.println("Welcome " + player1.getName() + "!");

        System.out.println("Please enter player 2's name.");
        Player player2 = new Player(InputHandler.nameScanner(scan), 'O');
        System.out.println("Welcome " + player2.getName() + "!");
        // System.out.println(player1.getName() + " " + player2.getName());
        TicTacToe game = new TicTacToe(player1, player2, scan);
        game.run();

        scan.close();
    }
}