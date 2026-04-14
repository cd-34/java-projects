package chopsticks;
import java.util.Scanner;

public class InputHandler {
    private Scanner scan;

    public InputHandler(Scanner scan) {
        this.scan = scan;
    }

    public static String nameScanner(Scanner scan) {
        while (true) {
            String inputName = scan.nextLine();

            if (inputName.isBlank()) {
                System.out.println("Invalid input - please enter your name.");
                continue;
            }
            return inputName;
        }
    }

    public static boolean playAgain(Scanner scan) {
        while (true) {
            System.out.println("Would you like to play again? Y / N");
            String response = scan.nextLine();
            
            if (response.toUpperCase().equals("Y")) {
                return true;
            } else if (response.toUpperCase().equals("N")) {
                return false;
            } else {
                System.out.println("Invalid input.");
            }
        }
    }
}