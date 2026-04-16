package chopsticks;

public class Player {
    private final String name;
    private int wins = 0;
    private int leftHand;
    private int rightHand;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getLeftHand() {
        return leftHand;
    }

    public void setLeftHand(int left) {
        leftHand = left;
    }

    public int getRightHand() {
        return rightHand;
    }

    public void setRightHand(int right) {
        rightHand = right;
    }

    public int getWins() {
        return wins;
    }

    public void incrementWins() {
        wins++;
    }
}