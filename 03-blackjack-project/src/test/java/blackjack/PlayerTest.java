package blackjack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player("Clear");
    }

    @Test
    public void testPlayerName() {
        assertEquals("Clear", player.getName());
    }

    @Test
    public void testInitialWins() {
        assertEquals(0, player.getWins());
    }

    @Test
    public void testAddCard() {
        player.addCard(new Card(Rank.FIVE, Suit.HEARTS));
        assertEquals(5, player.getHandValue());
    }

    @Test 
    public void testAddCardAce() {
        player.addCard(new Card(Rank.ACE, Suit.HEARTS));
        assertEquals(11, player.getHandValue());
    }

    @Test 
    public void testClearHand() {
        player.addCard(new Card(Rank.FIVE, Suit.HEARTS));
        player.clearHand();
        assertEquals(0, player.getHandValue());
    }
}
