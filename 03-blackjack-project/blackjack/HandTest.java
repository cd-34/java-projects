package blackjack;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HandTest {
    @Test
    public void testHandValue() {
        Hand hand = new Hand();
        hand.addCard(new Card(Rank.KING, Suit.HEARTS));
        hand.addCard(new Card(Rank.SEVEN, Suit.CLUBS));
        assertEquals(17, hand.getValue());
    }

    @Test
    public void testAce() {
        Hand hand = new Hand();
        hand.addCard(new Card(Rank.KING, Suit.HEARTS));
        hand.addCard(new Card(Rank.TEN, Suit.CLUBS));
        hand.addCard(new Card(Rank.ACE, Suit.DIAMONDS));
        assertEquals(21, hand.getValue());
    }
}