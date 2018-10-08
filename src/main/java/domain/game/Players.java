package domain.game;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Players {

    private final Set<Player> players;

    private Players(Player... players) {
        this.players = new HashSet<>();

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
        return new HashSet<>(players);
    }
}
