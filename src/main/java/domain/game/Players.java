package domain.game;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.stream.Stream;

public class Players {

    private final Queue<Player> players;

    private Players(Player... players) {
        this.players = new ArrayDeque<>();

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
        Player next = players.poll();
        players.add(next);
        return new Turn(next);
    }
}
