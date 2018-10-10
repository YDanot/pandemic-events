package domain.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Players {

    private final List<Player> players;

    private Players(Player... players) {
        this.players = new ArrayList<>();

        Stream.of(players).forEach(this.players::add);
        if (this.players.size() < 2 || this.players.size() > 4) {
            throw new IllegalArgumentException("This game should be played at 2, 3 or 4");
        }
    }

    public static Players of(Player... players) {
        return new Players(players);
    }

    public int count() {
        return players.size();
    }

    public Iterable<Player> get() {
        return new ArrayList<>(players);
    }

    public Turn turn() {
        Player next = players.iterator().next();
        players.remove(next);
        players.add(next);
        return new Turn(next);
    }
}
