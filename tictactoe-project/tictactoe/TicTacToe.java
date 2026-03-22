package tictactoe;
import java.util.Scanner;

public class TicTacToe {
    private Player player1;
    private Player player2;
    private boolean player1Turn = true; 
    private char[][] board = new char[3][3];
    private int turnCount = 1;
    private Scanner scan; 

    public TicTacToe(Player player1, Player player2, Scanner scan) {
        this.player1 = player1;
        this.player2 = player2;
        this.scan = scan;
    }

    public void initGame() {
        // Initialize char[][] board to ABCEDFGHI
        String boardLetters = "ABCDEFGHI";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = boardLetters.charAt(i * 3 + j);
            }
        }
        turnCount = 1;
    }

    // not sure if this is what's meant by changing printBoard() to a toString()
    @Override
    public String toString() {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("----------\n");
        stringbuilder.append(" ").append(board[0][0]).append(" | ").append(board[0][1]).append(" | ").append(board[0][2]).append("\n");
        stringbuilder.append("----------\n");
        stringbuilder.append(" ").append(board[1][0]).append(" | ").append(board[1][1]).append(" | ").append(board[1][2]).append("\n");
        stringbuilder.append("----------\n");
        stringbuilder.append(" ").append(board[2][0]).append(" | ").append(board[2][1]).append(" | ").append(board[2][2]).append("\n");
        stringbuilder.append("----------");
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
        if (player1Turn) {
            System.out.println(player1.getName() + "'s turn (X)! Enter your move.");
        } else {
            System.out.println(player2.getName() + "'s turn (O)! Enter your move.");
        }  
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
            playGame();
            playing = playAgain();
        }
    }

    public void playGame() {
        while(true) {
            System.out.println(this);
            printTurn();
            
            char move = moveScanner();

            if (!boardChange(move)) {
                continue;
            }

            if (hasWon()) {
                return;
            }
            // maybe this should be in hasWon()
            // but when it was in there, it wouldn't reinitialize the game upon a tie
            if (turnCount > 9) {
                System.out.println("Board has been filled, it's a tie!");
                return;
            }
        }
    }

    public char moveScanner() {
        while (true) {
            String moveString = scan.nextLine();
        
            if (moveString.isBlank()) {
                System.out.println("Please enter a character."); 
                continue;
            } else if (moveString.length() > 1) {
                System.out.println("Please enter one character.");
                continue;
            } else if (Character.getNumericValue(moveString.charAt(0)) < 10 || Character.getNumericValue(moveString.charAt(0)) > 18) {
                System.out.println("Enter a legal letter from A to I");
                continue;
            }

            return(Character.toUpperCase(moveString.charAt(0)));
        }
    }

    public boolean boardChange(char input) {
        int row = -1;
        int col = -1;
        switch(input) {
            case 'A': 
                row = 0; col = 0;
                break;
            case 'B': 
                row = 0; col = 1; 
                break;
            case 'C': 
                row = 0; col = 2; 
                break;
            case 'D': 
                row = 1; col = 0; 
                break;
            case 'E': 
                row = 1; col = 1; 
                break;
            case 'F': 
                row = 1; col = 2; 
                break;
            case 'G': 
                row = 2; col = 0; 
                break;
            case 'H': 
                row = 2; col = 1; 
                break;
            case 'I': 
                row = 2; col = 2; 
                break;
        } 

        if (row != -1 && col != -1 && board[row][col] == input) {
            board[row][col] = player1Turn ? 'X' : 'O';
            player1Turn = !player1Turn;
            turnCount++;
            return true;
        } else {
            System.out.println("Already occupied! Pick another time.");
            return false;
        }
    }

    public boolean hasWon() {
        // three horizontal win conditions
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                printWinner(board[i][0]);
                return true;
            }
        }
        // three vertical win conditions
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == board[1][j] && board[0][j] == board[2][j]) {
                printWinner(board[0][j]);
                return true;
            }
        }
        // two diagonal win conditions
        if (board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            printWinner(board[0][0]);
            return true;
        }
        if (board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            printWinner(board[0][2]);
            return true;
        }
        return false;
    }

    public void printWinner(char symbol) {
        String winner;
        if (symbol == 'X') {
            winner = player1.getName();
            player1.incrementWins();
        } else {
            winner = player2.getName();
            player2.incrementWins();
        }
        System.out.println(winner + " wins!");
        System.out.println("Scores:");
        System.out.println(player1.getName() + ": " + player1.getWins() + " | " 
            + player2.getName() + ": " + player2.getWins());
    }

    public boolean playAgain() {
        while (true) {
            System.out.println("Would you like to play again? Y / N");
            Scanner sc = new Scanner(System.in);
            String newGame = sc.nextLine();
            
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