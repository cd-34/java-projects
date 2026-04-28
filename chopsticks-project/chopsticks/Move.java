package chopsticks;

public class Move {
    public enum Type {
        ATTACK,
        SPLIT
    }

    private Type type;

    // For attacks
    private Side startHand; 
    private Side endHand;   

    // For splits
    private int left;
    private int right;

    // maybe temporary for debugging purposes, might remove in final version?
    @Override
    public String toString() {
        if (type == Type.ATTACK) {
            return "ATTACK: " + startHand + " -> " + endHand;
        } else if (type == Type.SPLIT) {
            return "SPLIT: " + left + " | " + right;
        }
        return "Invalid move";
    }

    public static Move attack(Side startHand, Side endHand) {
        Move move = new Move();
        move.type = Type.ATTACK;
        move.startHand = startHand;
        move.endHand = endHand;
        return move;
    }

    public static Move split(int left, int right) {
        Move move = new Move();
        move.type = Type.SPLIT;
        move.left = left;
        move.right = right;
        return move;
    }

    public Type getType() {
        return type;
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