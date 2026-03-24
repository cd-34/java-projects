// pr branch test
// to see if edits show up properly 
package tictactoe;
import java.util.Scanner;

public class TicTacToe {
    private Player player1;
    private Player player2;
    private boolean player1Turn = true;
    private int tictactoeSize = 3;
    private char[][] board = new char[tictactoeSize][tictactoeSize];
    private int turnCount = 1;
    private Scanner scan;

    public TicTacToe(Player player1, Player player2, Scanner scan) {
        this.player1 = player1;
        this.player2 = player2;
        this.scan = scan;
    }

    public void initGame() {
        char blank = '~';
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = blank;
            }
        }
        turnCount = 1;
        // player1Turn = true;
    }

    @Override
    public String toString() {
        StringBuilder stringbuilder = new StringBuilder();

        stringbuilder.append("    A   B   C\n");
        stringbuilder.append("  -------------\n");
        stringbuilder.append("3 | ").append(board[0][0]).append(" | ").append(board[0][1]).append(" | ").append(board[0][2]).append(" |\n");
        stringbuilder.append("  -------------\n");
        stringbuilder.append("2 | ").append(board[1][0]).append(" | ").append(board[1][1]).append(" | ").append(board[1][2]).append(" |\n");
        stringbuilder.append("  -------------\n");
        stringbuilder.append("1 | ").append(board[2][0]).append(" | ").append(board[2][1]).append(" | ").append(board[2][2]).append(" |\n");
        stringbuilder.append("  -------------");

        return stringbuilder.toString();
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

    public void printTurn() {
        String playerName = (turnCount % 2 != 0) ? player1.getName() : player2.getName();
        String symbol = (turnCount % 2 != 0) ? "X" : "O";
        System.out.println(playerName + "'s turn (" + symbol + ")! Enter your move (e.g. A1, B2, C3):");
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter player 1's name.");
        Player player1 = new Player(nameScanner(scan));
        System.out.println("Welcome " + player1.getName() + "!");

        System.out.println("Please enter player 2's name.");
        Player player2 = new Player(nameScanner(scan));
        System.out.println("Welcome " + player2.getName() + "!");
        // System.out.println(player1.getName() + " " + player2.getName());
        TicTacToe game = new TicTacToe(player1, player2, scan);
        game.run();

        scan.close();
    }

    public void run() {
        boolean playing = true;
        while (playing) {
            initGame();
            Player winner = playGame();
            if (winner != null) {
                printWinner(winner);
            }
            playing = playAgain();
        }
    }

    public Player playGame() {
        while(true) {
            System.out.println(this);
            printTurn();
            
            char[] move = moveScanner(scan);

            if (!boardChange(move)) {
                continue;
            }

            Player winner = hasWon();
            if (winner != null) {
                return winner;
            }

            if (turnCount > (tictactoeSize * tictactoeSize)) {
                System.out.println("Board has been filled, it's a tie!");
                return null;
            }
        }
    }

    public static char[] moveScanner(Scanner scan) {
        while (true) {
            String moveString = scan.nextLine().toUpperCase().trim();

            if (moveString.length() != 2) {
                System.out.println("Please enter a valid input of two characters (e.g. A1, B2, C3):");
                continue;
            }

            char col = moveString.charAt(0);
            char row = moveString.charAt(1);

            if (col < 'A' || col > 'C') {
                System.out.println("Column must be A, B, or C:");
                continue;
            }

            if (row < '1' || row > '3') {
                System.out.println("Row must be be 1, 2, or 3:");
                continue;
            }

            return new char[]{col, row};
        }
    }

    public boolean boardChange(char[] input) {
        char col = input[0]; // letter
        char row = input[1]; // number
        int colIndex = col - 'A'; // C - A = 2 | B - A = 1 | A - A = 0
        int rowIndex = '3' - row; // 3 - 3 = 0 or top row

        if (board[rowIndex][colIndex] == '~') {
            board[rowIndex][colIndex] = player1Turn ? 'X' : 'O';
            player1Turn = !player1Turn;
            turnCount++;
            return true;
        } else {
            System.out.println("That tile is already occupied!");
            return false;
        }
    }

    public Player hasWon() {
        // three horizontal win conditions
        for (int i = 0; i < tictactoeSize; i++) {
            if (board[i][0] != '~' && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                return getWinner(board[i][0]);
            }
        }
        // three vertical win conditions
        for (int j = 0; j < tictactoeSize; j++) {
            if (board[0][j] != '~' && board[0][j] == board[1][j] && board[0][j] == board[2][j]) {
                return getWinner(board[0][j]);
            }
        }
        // two diagonal win conditions
        if (board[0][0] != '~' && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            return getWinner(board[0][0]);
        }
        if (board[0][2] != '~' && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            return getWinner(board[0][2]);
        }
        return null;
    }

    private Player getWinner(char symbol) {
        if (symbol == 'X') {
            return player1;
        } else {
            return player2;
        }
    }

    public void printWinner(Player winner) {
        winner.incrementWins();

        System.out.println(winner.getName() + " wins!");
        System.out.println("Scores:");
        System.out.println(player1.getName() + ": " + player1.getWins() + " | " 
            + player2.getName() + ": " + player2.getWins());
    }

    public boolean playAgain() {
        while (true) {
            System.out.println("Would you like to play again? Y / N");
            String newGame = scan.nextLine();
            
            if (newGame.toUpperCase().equals("Y")) {
                return true;
            } else if (newGame.toUpperCase().equals("N")) {
                return false;
            } else {
                System.out.println("Invalid input.");
            }
        }
    }
}