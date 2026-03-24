package tictactoe;
import java.util.Scanner;

public class TicTacToe {
    private Player player1;
    private Player player2;
    private boolean player1Turn = true;
    private int boardSize = 3;
    private char[][] board = new char[boardSize][boardSize];
    private char blank = '~';
    private int turnCount = 1;
    private Scanner scan;

    public TicTacToe(Player player1, Player player2, Scanner scan) {
        this.player1 = player1;
        this.player2 = player2;
        this.scan = scan;
    }

    public void initGame() {
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
        // column labels (letters)
        stringbuilder.append("    ");
        for (int i = 0; i < boardSize; i++) {
            stringbuilder.append((char) ('A' + i)).append("   ");
        }
        stringbuilder.append("\n");
        
        stringbuilder.append(toStringBorder());
        
        // rows (numbers)
        for (int j = boardSize; j >= 1; j--) {
            // prints the number on the left
            stringbuilder.append(j).append(" | ");
            // prints the board with the blank '~'
            for (int k = 0; k < boardSize; k++) {
            stringbuilder.append(board[boardSize - j][k]).append(" | ");
            }
            stringbuilder.append("\n");
            stringbuilder.append(toStringBorder());
        }
        return stringbuilder.toString();
    }

    public String toStringBorder() {
        // creates "------" border
        StringBuilder border = new StringBuilder();
        border.append("  ");
        for (int i = 0; i < boardSize; i++) {
            border.append("----");
        }
        border.append("\n");
        return border.toString();
    }

    public void printTurn() {
        String playerName = (turnCount % 2 != 0) ? player1.getName() : player2.getName();
        String symbol = (turnCount % 2 != 0) ? "X" : "O";
        System.out.println(playerName + "'s turn (" + symbol + ")! Enter your move (e.g. A1, B2, C3):");
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter player 1's name.");
        Player player1 = new Player(InputHandler.nameScanner(scan));
        System.out.println("Welcome " + player1.getName() + "!");

        System.out.println("Please enter player 2's name.");
        Player player2 = new Player(InputHandler.nameScanner(scan));
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
            
            int[] move = InputHandler.moveScanner(scan, boardSize);

            if (!boardChange(move)) {
                continue;
            }

            Player winner = hasWon();
            if (winner != null) {
                return winner;
            }

            if (turnCount > boardSize * boardSize) {
                checkTie();
                return null;
            }
        }
    }

    public void checkTie() {
        System.out.println("Board has been filled, it's a tie!");
    }

    public boolean boardChange(int[] input) {
        if (board[input[0]][input[1]] == blank) {
            board[input[0]][input[1]] = player1Turn ? 'X' : 'O';
            player1Turn = !player1Turn;
            turnCount++;
            return true;
        } else {
            System.out.println("That tile is already occupied!");
            return false;
        }
    }

    public Player hasWon() {
        // horizontal win conditions
        for (int i = 0; i < boardSize; i++) {
            char first = board[i][0];
            // skip current horizontal if first tile is blank
            if (first == blank) {
                continue;
            }

            boolean win = true;
            for (int j = 1; j < boardSize; j++) {
                if (board[i][j] != first) {
                    win = false;
                    break;
                }
            }

            if (win) {
                return getWinner(first);
            }

        }
        // vertical win conditions
        for (int j = 0; j < boardSize; j++) {
            char first = board[0][j];

            if (first == blank) {
                continue;
            }

            boolean win = true;
            for (int i = 1; i < boardSize; i++) {
                if (board[i][j] != first) {
                    win = false; 
                    break;
                }
            }

            if (win) {
                return getWinner(first);
            }
        }

        // diagonal win condition from top left to bottom right
        if (board[0][0] != blank) {
            boolean win = true;
            for (int i = 1; i < boardSize; i++) {
                if (board[i][i] != board[0][0]) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return getWinner(board[0][0]);
            }
        }

        // diagonal win conditon from bottom left to top right
        if (board[0][boardSize - 1] != blank) {
            boolean win = true;
            for (int i = 1; i < boardSize; i++) {
                if (board[i][boardSize - 1 - i] != board[0][boardSize - 1]) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return getWinner(board[0][boardSize - 1]);
            }
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