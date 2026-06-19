package chopsticks;

public class Chopsticks {
    private Player player1;
    private Player player2;
    private Player winner = null;
    private int turnCount = 1;

    public Chopsticks(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override 
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(makeHorizontalBorder());
        sb.append("| Left  | Right |");
        sb.append("\n");

        sb.append(makeHorizontalBorder());
        sb.append("|   " + player2.getLeftHand().getFingers() + "   |   " + player2.getRightHand().getFingers() + "   |  " + player2.getName());
        sb.append("\n");

        sb.append(makeHorizontalBorder());
        sb.append("|   " + player1.getLeftHand().getFingers() + "   |   " + player1.getRightHand().getFingers() + "   |  " + player1.getName());

        sb.append("\n");
        sb.append(makeHorizontalBorder());
        
        return sb.toString();
    }

    public String makeHorizontalBorder() {
        StringBuilder border = new StringBuilder();
        border.append("|---------------|");
        border.append("\n");
        return border.toString();
    }

    // starts a game by initializing the player's hands
    // and printing out the "board"
    public void init() {
        player2.getLeftHand().set(1);
        player2.getRightHand().set(1);

        player1.getLeftHand().set(1);
        player1.getRightHand().set(1);
    }

    public Player getCurrentPlayer() {
        return (turnCount % 2 != 0) ? player1 : player2;
    }

    public Player getOpposingPlayer() {
        return (turnCount % 2 == 0) ? player1 : player2;
    }

    public int incrementTurnCount() {
        return turnCount++;
    }

    public void applyMove(Move move) {
        if (move.getType() == Move.MoveType.ATTACK) {
            applyAttack(move);
        } else {
            applySplit(move);
        }
    }

    public void applyAttack(Move move) {
        Player current = getCurrentPlayer();
        Player opponent = getOpposingPlayer();

        Hand attacking = (move.getStartHand() == Side.LEFT) ? current.getLeftHand() : current.getRightHand();

        Hand target = (move.getEndHand() == Side.LEFT) ? opponent.getLeftHand() : opponent.getRightHand();

        target.add(attacking.getFingers());
    }

    public void applySplit(Move move) {
        Player current = getCurrentPlayer();
        current.getLeftHand().set(move.getLeft());
        current.getRightHand().set(move.getRight());
    }

    public boolean isGameOver() {
        return player1.isDead() || player2.isDead();
    }

    public Player getWinner() {
        if (player1.isDead()) {
            return player2;
        } 
        if (player2.isDead()) {
            return player1;
        }
        return null;
    }

    public boolean isValidMove(Move move) {
        if (move.getType() == Move.MoveType.ATTACK) {
            return isValidAttack(move);
        } else {
            return isValidSplit(move);
        }
    }

    private boolean isValidAttack(Move move) {
        Player current = getCurrentPlayer();
        Player opponent = getOpposingPlayer();

        Hand attackingHand = (move.getStartHand() == Side.LEFT) ? current.getLeftHand() : current.getRightHand();

        Hand targetHand = (move.getEndHand() == Side.LEFT) ? opponent.getLeftHand() : opponent.getRightHand();

        if (attackingHand.isDead()) {
            System.out.println("You cannot attack with a dead hand.");
            return false;
        }

        if (targetHand.isDead()) {
            System.out.println("You cannot attack a dead hand.");
            return false;
        }
        return true;
    }

    private boolean isValidSplit(Move move) {
        Player current = getCurrentPlayer();
        int currentTotal = current.getLeftHand().getFingers() + current.getRightHand().getFingers();
        int left = move.getLeft();
        int right = move.getRight();
        // validating range
        if (left < 1 || left > 4 || right < 1 || right > 4) {
            System.out.println("Both hands must be between 1 and 4");
            return false;
        }

        // validating total
        if (left + right != currentTotal) {
            System.out.println("Split must preserve total.");
            return false;
        }

        // validating not identical
        if ((left == current.getLeftHand().getFingers() && right == current.getRightHand().getFingers())
            || (left == current.getRightHand().getFingers() && right == current.getLeftHand().getFingers())) {
            System.out.println("Split is identical to current position");
            return false;
        }
        return true;
    }
}