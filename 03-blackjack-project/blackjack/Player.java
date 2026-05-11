package blackjack;

public class Player {
    private final String name;
    private int wins = 0;
    private Hand hand;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
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

    public Hand getHand() {
        return hand;
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }
}