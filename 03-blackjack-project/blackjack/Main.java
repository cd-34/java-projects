package blackjack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Scanner scan = new Scanner(System.in);

        ConsoleIO consoleIO = new ConsoleIO(scan);

        BlackjackApp app = new BlackjackApp(consoleIO);
        app.addPlayers(scan);

        scan.close();
    }
}