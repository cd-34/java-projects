package chopsticks;

public class Move {
    public enum MoveType {
        ATTACK,
        SPLIT
    }

    private MoveType moveType;

    // For attacks
    private Side startHand; 
    private Side endHand;   

    // For splits
    private int left;
    private int right;

    // maybe temporary for debugging purposes, might remove in final version?
    @Override
    public String toString() {
        if (moveType == MoveType.ATTACK) {
            return "ATTACK: " + startHand + " -> " + endHand;
        } else if (moveType == MoveType.SPLIT) {
            return "SPLIT: " + left + " | " + right;
        }
        return "Invalid move";
    }

    public static Move attack(Side startHand, Side endHand) {
        Move move = new Move();
        move.moveType = MoveType.ATTACK;
        move.startHand = startHand;
        move.endHand = endHand;
        return move;
    }

    public static Move split(int left, int right) {
        Move move = new Move();
        move.moveType = MoveType.SPLIT;
        move.left = left;
        move.right = right;
        return move;
    }

    public MoveType getType() {
        return moveType;
    }

    public Side getStartHand() {
        return startHand;
    }

    public Side getEndHand() {
        return endHand;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }
}