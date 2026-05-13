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

    public int getHandValue() {
        return hand.getValue();
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public boolean isDealerBust() {
        return hand.isBust();
    }

    // should probably change this to return a boolean for reuse later 
    public void playTurn(Deck deck) {
        while (getHandValue() < 17) {
            Card card = deck.deal();
            addCard(card);
            System.out.println("Dealer hits: \n" + card);
        }

        if (isDealerBust()) {
            System.out.println("Dealer busts with " + getHandValue());
        } else {
            System.out.println("Dealer stands at " + getHandValue());
        }
    }
}