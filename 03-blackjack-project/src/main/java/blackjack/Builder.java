package blackjack;
import java.util.ArrayList;
import java.util.List;

public class Builder {
    private final List<String> playerNames = new ArrayList<>();

    public Builder() {

    }

    // List allows us to continually add names as the object is being built upon
    public Builder join(String name) {
        playerNames.add(name);
        return this;
    }

    // Creates an array (immutable size) for the number of players 
    // that we know from addPlayers() in BlackjackApp
    // then setPlayers() populates private Player[] players in Blackjack
    public Blackjack build() {
        Player[] players = new Player[playerNames.size()];
        for (int i = 0; i < playerNames.size(); i++) {
            players[i] = new Player(playerNames.get(i));
        }
        Blackjack blackjack = new Blackjack();
        blackjack.setPlayers(players);
        return blackjack;
    }
}