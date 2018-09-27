package domain.game;

import domain.network.CityName;
import domain.role.Role;
import infra.World;

import java.util.HashMap;
import java.util.Map;

public class PawnLocations {

    private final Map<Role, CityName> locations = new HashMap<>();

    public PawnLocations(CityName cityName, Role... roles) {
        for (Role role : roles) {
            locations.put(role, cityName);
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
}
