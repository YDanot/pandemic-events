package domain.actions.movement;

import domain.actions.ActionImpossible;
import domain.game.Player;
import domain.network.CityName;
import infra.World;

import java.util.Optional;

public class ShuttleFly extends MovementAction {

    public ShuttleFly(CityName destination) {
        super(destination);
    }

    @Override
    public Optional<ActionImpossible> move(Player player) {
        Optional<ActionImpossible> movable = Optional.empty();
        CityName from = World.board.locations.locationsOf(player.role());
        if (World.board.researchStations.builtOn(from) && World.board.researchStations.builtOn(destination)) {
            World.board.locations.move(player.role(), destination);
        } else {
            movable = Optional.of(ActionImpossible.SHUTTLE_NO_STATION_ON_CITIES);
        }
        return movable;
    }

}
