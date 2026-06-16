package blackjack;

public class Dealer extends Participant {
    private final String name = "Dealer";

    public Dealer() {
        super();
        this.hand = new Hand();
    }

    public String getName() {
        return name;
    }

    public void playTurn(Deck deck) {
        while (getHandValue() < 17) {
            Card card = deck.deal();
            addCard(card);
            System.out.println("Dealer hits: \n" + card);
        }

        if (isBust()) {
            System.out.println("Dealer busts with " + getHandValue() + ".");
        } else {
            System.out.println("Dealer stands at " + getHandValue() + ".");
        }
    }
}