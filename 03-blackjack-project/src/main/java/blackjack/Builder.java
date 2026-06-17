package blackjack;
import java.util.ArrayList;
import java.util.List;

public class Builder {
    private final List<String> playerNames = new ArrayList<>();

    public Builder() {

    }

    public Builder join(String name) {
        playerNames.add(name);
        return this;
    }

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