package blackjack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BlackjackTest {
    private Blackjack blackjack;

    @BeforeEach 
    public void setUp() {
        blackjack = new Blackjack();
        Player[] players = {new Player("Clear")};
        blackjack.init(players);
    }

    @Test
    public void testResetRoundDeckSize() {
        assertEquals(49, blackjack.getDeckSize());
        blackjack.dealCardToPlayer(0);
        assertEquals(48, blackjack.getDeckSize());
        blackjack.resetRound();
        assertEquals(52, blackjack.getDeckSize());
    }
}
