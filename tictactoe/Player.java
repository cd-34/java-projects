// this class will be called by tictactoe.java
// goal is to create a scanner for name and set a win counter 
// remember that I'll need to support two players
// player name immutable; win counter mutable 
package tictactoe;
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

    public int getWinCounter() {
        return winCounter;
    }

    public void incrementWins() {
        winCounter++;
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
}