package blackjack;

public class Dealer {
    private final String name = "Dealer";
    private Hand hand;

    public Dealer() {
        this.hand = new Hand();
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }
}