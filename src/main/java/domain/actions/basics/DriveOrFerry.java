package domain.actions.basics;

import domain.game.Player;
import domain.network.CityName;
import infra.World;

public class DriveOrFerry implements BasicAction {

    private CityName destination;

    public DriveOrFerry(CityName destination) {
        this.destination = destination;
    }

    @Override
    public void accept(Player player) {
        CityName from = World.board.locations.locationsOf(player.role());
        if (World.board.network.areLinked(from, destination)) {
            World.board.locations.move(player.role(), destination);
        } else {
            throw new ForbiddenMove(from, destination);
        }
    }

}
