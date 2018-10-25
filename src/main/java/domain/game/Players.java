package domain.game;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

public class Players {

    private final Queue<Player> players;
    private Turn currentTurn;

    public Players(List<Player> players) {
        this.players = new ArrayDeque<>();
        addPlayers(players.stream());
    }

    private Players(Player... players) {
        this.players = new ArrayDeque<>();

        addPlayers(Stream.of(players));
    }

    public static Players of(Player... players) {
        return new Players(players);
    }

    private void addPlayers(Stream<Player> players) {
        players.forEach(this.players::add);
        if (this.players.size() < 2 || this.players.size() > 4) {
            throw new IllegalArgumentException("This game should be played at 2, 3 or 4");
        }
    }

    public int count() {
        return players.size();
    }

    public List<Player> get() {
        return new ArrayList<>(players);
    }

    public Turn newTurn() {
        Player next = players.poll();
        players.add(next);
        currentTurn = new Turn(next);
        return currentTurn;
    }

    public Turn currentTurn() {
        return currentTurn == null ? newTurn() : currentTurn;
    }
}
