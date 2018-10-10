package domain.actions.basics;

import domain.game.Player;
import domain.network.CityName;
import infra.World;

public class ShuttleFly implements BasicAction {

    private CityName destination;

    public ShuttleFly(CityName destination) {
        this.destination = destination;
    }

    @Override
    public void accept(Player player) {
        CityName from = World.game.locations.locationsOf(player.role());
        if (World.game.researchStations.builtOn(from) && World.game.researchStations.builtOn(destination)) {
            World.game.locations.move(player.role(), destination);
        } else {
            throw new ForbiddenMove(from, destination);
        }
    }

}
