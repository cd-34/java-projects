package blackjack;

// to my understanding, abstract classes cannot create objects
// just used to provide partial implementation
// so for this, I'm removing a lot of duplicate code from Player and Dealer
// by having an abstract class provide partial implementation that covers both
public abstract class Participant {
    // protected allows visibility to subclasses (in this case player + dealer)
    protected Hand hand;

    public Participant() {
        this.hand = new Hand();
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public void clearHand() {
        hand.clear();
    }

    public Hand getHand() {
        return hand;

    }

    public int getHandValue() {
        return hand.getValue();
    }

    public boolean isBust() {
        return hand.isBust();
    }
}
