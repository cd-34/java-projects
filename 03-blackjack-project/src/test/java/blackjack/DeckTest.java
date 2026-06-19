package blackjack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

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
    public void testDeckHasAllRanks() {
        Map<Rank, Integer> rankCounts = new HashMap<>();
        while (!deck.isEmpty()) {
            Card card = deck.deal();
            // if rank isn't there, sets value as 1
            // if already exists, adds 1
            rankCounts.merge(card.getRank(), 1, Integer::sum);
        }
        for (Rank rank : Rank.values()) {
            assertEquals(4, rankCounts.get(rank));
        }
    }

    @Test
    public void testDeckHasAllSuits() {
        Map<Suit, Integer> suitCounts = new HashMap<>();
        while (!deck.isEmpty()) {
            Card card = deck.deal();
            suitCounts.merge(card.getSuit(), 1, Integer::sum);
        }
        for (Suit suit : Suit.values()) {
            assertEquals(13, suitCounts.get(suit));
        }
    }

    @Test 
    public void testDealReducesDeckSize() {
        deck.deal();
        assertEquals(51, deck.size());
    }
}
