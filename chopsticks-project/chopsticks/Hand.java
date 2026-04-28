package chopsticks;

public class Hand {
    private int fingers;

    public Hand(int fingers) {
        this.fingers = fingers;
    }

    public int getFingers() {
        return fingers;
    }

    public boolean isDead() {
        return fingers == 0;
    }

    public void add(int value) {
        fingers = fingers + value;

        if (fingers >= 5) {
            fingers = 0;
        } 
    }

    public void set(int value) {
        fingers = value;
    }
}