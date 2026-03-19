// note to self: to run the program, go into this folder with terminal and type
// java tictactoe.java
package tictactoe;
import java.util.Scanner;

public class TicTacToe {
    private Player player1;
    private Player player2;
    private boolean playerXTurn = true; 
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
        if (playerXTurn) {
            System.out.println("Player X's turn! Enter your move.");
        } else {
            System.out.println("Player O's turn! Enter your move.");
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
        initGame();
        printBoard();
        printTurn();
        moveScanner();
    }

    public void moveScanner() {
        // takes an input from the user
        // if it's an illegal input, will print out an error
        // tried to throw new errors instead of just printing them
        // but that would exit the program which is undesirable
        Scanner sc = new Scanner(System.in);
        String moveString = sc.nextLine();
    
        if (moveString.isBlank()) {
            System.out.println("Please enter a character."); 
            moveScanner();
        } else if (moveString.length() > 1) {
            System.out.println("Please enter one character.");
            moveScanner();
        } else if (Character.getNumericValue(moveString.charAt(0)) < 10 || Character.getNumericValue(moveString.charAt(0)) > 18) {
            System.out.println("Enter a legal letter from A to I");
            moveScanner();
        }

        // turn count to check if the board is filled up and the game needs to end
        turnCount++;
        boardChange(Character.toUpperCase(moveString.charAt(0)));
    }

    // this is a hideously long method
    // maybe a switch statement would look better?
    // tried to move the four methods into the helper method but didn't work
    // would have to change the helper method to void and not return anything
    // might make a new method nextTurn() to condense it all some more
    public void boardChange(char input) {
        if (input == 'A' && board[0][0] == 'A') {
            if (playerXTurn) {
                board[0][0] = 'X';
                playerXTurn = !playerXTurn;
            } else {
                board[0][0] = 'O';
                playerXTurn = !playerXTurn;
            }
            printBoard();
            winCondition();
            printTurn();
            moveScanner();
        } else if (input == 'B' && board[0][1] == 'B') {
            board[0][1] = boardChangeHelper();
            printBoard();
            winCondition();
            printTurn();
            moveScanner();
        } else if (input == 'C' && board[0][2] == 'C') {
            board[0][2] = boardChangeHelper();
            printBoard();
            winCondition();
            printTurn();
            moveScanner();
        } else if (input == 'D' && board[1][0] == 'D') {
            board[1][0] = boardChangeHelper();
            printBoard();
            winCondition();
            printTurn();
            moveScanner();
        } else if (input == 'E' && board[1][1] == 'E') {
            board[1][1] = boardChangeHelper();
            printBoard();
            winCondition();
            printTurn();
            moveScanner();
        } else if (input == 'F' && board[1][2] == 'F') {
            board[1][2] = boardChangeHelper();
            printBoard();
            winCondition();
            printTurn();
            moveScanner();
        } else if (input == 'G' && board[2][0] == 'G') {
            board[2][0] = boardChangeHelper();
            printBoard();
            winCondition();
            printTurn();
            moveScanner();
        } else if (input == 'H' && board[2][1] == 'H') {
            board[2][1] = boardChangeHelper();
            printBoard();
            winCondition();
            printTurn();
            moveScanner();
        } else if (input == 'I' && board[2][2] == 'I') {
            board[2][2] = boardChangeHelper();
            printBoard();
            winCondition();
            printTurn();
            moveScanner();
        } else {
            printBoard();
            System.out.println("Already occupied! Pick another tile.");
            printTurn();
            moveScanner();
        }
    }

    public char boardChangeHelper() {
        if (playerXTurn) {
            playerXTurn = !playerXTurn;
            return 'X';
        } else {
            playerXTurn = !playerXTurn;
            return 'O';
        }
    }

    public void winCondition() {
        // three horizontal win conditions
        if (board[0][0] == board[0][1] && board[0][0] == board[0][2]) {
            System.out.println("Player " + board[0][0] + " wins!");
            System.exit(0);
        }
        if (board[1][0] == board[1][1] && board[1][0] == board[1][2]) {
            System.out.println("Player " + board[1][0] + " wins!");
            System.exit(0);
        }
        if (board[2][0] == board[2][1] && board[2][0] == board[2][2]) {
            System.out.println("Player " + board[2][0] + " wins!");
            System.exit(0);
        }
        // three vertical win conditions
        if (board[0][0] == board[1][0] && board[0][0] == board[2][0]) {
            System.out.println("Player " + board[0][0] + " wins!");
            System.exit(0);
        }
        if (board[0][1] == board[1][1] && board[0][1] == board[2][1]) {
            System.out.println("Player " + board[0][1] + " wins!");
            System.exit(0);
        }
        if (board[0][2] == board[1][2] && board[0][2] == board[2][2]) {
            System.out.println("Player " + board[0][2] + " wins!");
            System.exit(0);
        }
        // two diagonal win conditions
        if (board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            System.out.println("Player " + board[0][0] + " wins!");
            System.exit(0);
        }
        if (board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            System.out.println("Player " + board[0][2] + " wins!");
            System.exit(0);
        }
        // board filled and nobody wins condition
        if (turnCount > 9) {
            System.out.println("Board has been filled, there is no winner.");
            System.exit(0);
        }
    }
}