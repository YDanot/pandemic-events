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
    public void act(Player player) {
        CityName from = World.board.locations.locationsOf(player.role());
        if (World.board.researchStations.builtOn(from) && World.board.researchStations.builtOn(destination)) {
            World.board.locations.move(player.role(), destination);
        } else {
            throw new ForbiddenMove(from, destination);
        }
    }

}
