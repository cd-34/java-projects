// this class will be called by tictactoe.java
// goal is to create a scanner for name and set a win counter 
// remember that I'll need to support two players
// player name immutable; win counter mutable 

import java.util.Scanner;

public class Player {
    private final String name;
    private int winCounter = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static String nameScanner() {
        Scanner sc = new Scanner(System.in);
        String inputName = sc.nextLine();

        if (inputName.isBlank()) {
            System.out.println("Invalid input - please enter your name.");
            nameScanner();
        }

        return inputName;
    }

    public static void main(String[] args) {
        System.out.println("Please enter player 1's name.");
        Player player1 = new Player(nameScanner());
        System.out.println(player1.getName());

        System.out.println("Please enter player 2's name.");
        Player player2 = new Player(nameScanner());
        System.out.println(player2.getName());
    }
}