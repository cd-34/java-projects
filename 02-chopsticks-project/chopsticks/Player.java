package chopsticks;

public class Player {
    private final String name;
    private int wins = 0;
    private Hand leftHand;
    private Hand rightHand;

    public Player(String name) {
        this.name = name;
        this.leftHand = new Hand(1);
        this.rightHand = new Hand(1);
    }

    public String getName() {
        return name;
    }

    public Hand getLeftHand() {
        return leftHand;
    }

    public void setLeftHand(Hand left) {
        leftHand = left;
    }

    public Hand getRightHand() {
        return rightHand;
    }

    public void setRightHand(Hand right) {
        rightHand = right;
    }

    public int getWins() {
        return wins;
    }

    public void incrementWins() {
        wins++;
    }

    public boolean isDead() {
        return getLeftHand().isDead() && getRightHand().isDead();
    }
}