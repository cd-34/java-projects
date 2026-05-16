package blackjack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    private Deck deck;

    @BeforeEach 
    public void setUp() {
        deck = new Deck();
    }

    @Test
    public void testDeck52Cards() {
        assertEquals(52, deck.size());
    }

    @Test 
    public void testDealReducesDeckSize() {
        deck.deal();
        assertEquals(51, deck.size());
    }
}
