package blackjack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // temporary so I can see the deck being printed out
        Deck deck = new Deck();
        deck.shuffle();
        for (Card card : deck.getCards()) {
            System.out.println(card + " " + card.getValue());
        }

        Scanner scan = new Scanner(System.in);

        ConsoleIO consoleIO = new ConsoleIO(scan);

        BlackjackApp app = new BlackjackApp(consoleIO);
        app.addPlayers(scan);

        scan.close();
    }
}