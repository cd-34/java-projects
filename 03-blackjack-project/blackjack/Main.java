package blackjack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // next 5 lines are just temporary so I can see the deck being shuffled and printed out
        Deck deck = new Deck();
        deck.shuffle();
        for (Card card : deck.getCards()) {
            System.out.println(card + "\n");
        }

        Scanner scan = new Scanner(System.in);
        ConsoleIO consoleIO = new ConsoleIO(scan);
        BlackjackApp app = new BlackjackApp(consoleIO);
        app.addPlayers(scan);
        // app.run();

        scan.close();
    }
}