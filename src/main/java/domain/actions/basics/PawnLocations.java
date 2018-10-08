package domain.actions.basics;

import domain.game.Player;
import domain.game.Players;
import domain.network.CityName;
import domain.role.Role;
import infra.World;

import java.util.HashMap;
import java.util.Map;

public class PawnLocations {

    private final Map<Role, CityName> locations = new HashMap<>();

    public PawnLocations(CityName cityName, Players players) {
        this(cityName, players.get());
    }

    private PawnLocations(CityName cityName, Iterable<Player> roles) {
        for (Player player : roles) {
            locations.put(player.role(), cityName);
        }
    }


    public void drive(Role role, CityName destination) throws ForbiddenMoveException {
        CityName from = locationsOf(role);
        if (World.game.network.areLinked(from, destination)) {
            locations.put(role, destination);
        } else {
            throw new ForbiddenMoveException(from, destination);
        }
    }

    public CityName locationsOf(Role role) {
        return locations.get(role);
    }

    public void move(Role role, CityName cityName) {
        locations.put(role, cityName);
    }

    public void shuttleFlight(Role role, CityName destination) {
        CityName from = locationsOf(role);
        if (World.game.researchStations.builtOn(from) && World.game.researchStations.builtOn(destination)) {
            locations.put(role, destination);
        } else {
            throw new ForbiddenMoveException(from, destination);
        }
    }
}
