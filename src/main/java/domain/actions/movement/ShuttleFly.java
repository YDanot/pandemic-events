package domain.actions.movement;

import domain.game.Player;
import domain.network.CityName;
import infra.World;

public class ShuttleFly extends MovementAction {

    public ShuttleFly(CityName destination) {
        super(destination);
    }

    @Override
    public void move(Player player) {
        CityName from = World.board.locations.locationsOf(player.role());
        if (World.board.researchStations.builtOn(from) && World.board.researchStations.builtOn(destination)) {
            World.board.locations.move(player.role(), destination);
        } else {
            throw new ForbiddenMove(player.role(), destination);
        }
    }

}
