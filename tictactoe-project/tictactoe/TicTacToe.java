// note to self: to run the program, go into this folder with terminal and type
// javac tictactoe/*.java
// java tictactoe.TicTacToe

package tictactoe;
import java.util.Scanner;

public class TicTacToe {
    private Player player1;
    private Player player2;
    private boolean player1Turn = true; 
    private char[][] board = new char[3][3];
    private int turnCount = 1;

    public TicTacToe(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
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

    public void printBoard() {
        System.out.println("----------");
        System.out.println(" " + board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println("----------");
        System.out.println(" " + board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println("----------");
        System.out.println(" " + board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
        System.out.println("----------");
    }

    public void printTurn() {
        if (player1Turn) {
            System.out.println(player1.getName() + "'s turn (X)! Enter your move.");
        } else {
            System.out.println(player2.getName() + "'s turn (O)! Enter your move.");
        }  
    }

    public static void main(String[] args) {
        System.out.println("Please enter player 1's name.");
        Player player1 = new Player(Player.nameScanner());
        System.out.println("Welcome " + player1.getName() + "!");

        System.out.println("Please enter player 2's name.");
        Player player2 = new Player(Player.nameScanner());
        System.out.println("Welcome " + player2.getName() + "!");
        // System.out.println(player1.getName() + " " + player2.getName());
        TicTacToe game = new TicTacToe(player1, player2);
        game.run();
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
            printBoard();
            printTurn();
            
            char move = moveScanner();

            if (!boardChange(move)) {
                continue;
            }

            // printBoard();

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
            Scanner sc = new Scanner(System.in);
            String moveString = sc.nextLine();
        
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
        // playAgain();
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