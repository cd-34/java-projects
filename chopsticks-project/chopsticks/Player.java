package chopsticks;

public class Player {
    private final String name;
    private int wins = 0;
    private int leftHand = 1;
    private int rightHand = 1;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getLeftHand() {
        return leftHand;
    }

    public int getRightHand() {
        return rightHand;
    }

    public int getWins() {
        return wins;
    }

    public void incrementWins() {
        wins++;
    }
}