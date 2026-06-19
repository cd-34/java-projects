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
        blackjack.setPlayers(players);
    }

    @Test
    public void testResetRoundDeckSize() {
        blackjack.initialDeal();
        assertEquals(49, blackjack.getDeckSize());
        blackjack.hitCurrentPlayer();
        assertEquals(48, blackjack.getDeckSize());
        blackjack.resetRound();
        assertEquals(52, blackjack.getDeckSize());
    }
}
