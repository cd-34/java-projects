package blackjack;

public class Player extends Participant {
    private final String name;
    private int wins = 0;

    public Player(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public void incrementWins() {
        wins++;
    }
}