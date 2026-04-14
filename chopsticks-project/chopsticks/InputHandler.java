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

    
}