package domain.board;

import domain.game.Player;
import domain.game.Players;
import domain.network.CityName;
import domain.role.Role;

import java.util.HashMap;
import java.util.Map;

public class PawnLocations {

    private final Map<Role, CityName> locations = new HashMap<>();

    PawnLocations() {
    }

    public PawnLocations(CityName cityName, Players players) {
        this(cityName, players.get());
    }

    private PawnLocations(CityName cityName, Iterable<Player> roles) {
        for (Player player : roles) {
            locations.put(player.role(), cityName);
        }
    }

    public CityName locationsOf(Role role) {
        return locations.get(role);
    }

    public void move(Role role, CityName cityName) {
        locations.put(role, cityName);
    }
}
