package domain.actions.movement;

import domain.actions.ActionImpossible;
import domain.game.Player;
import domain.network.CityName;
import infra.World;

import java.util.Optional;

public class DriveOrFerry extends MovementAction {

    public DriveOrFerry(CityName destination) {
        super(destination);
    }

    @Override
    public Optional<ActionImpossible> move(Player player) {
        Optional<ActionImpossible> movable = Optional.empty();
        CityName from = World.board.locations.locationsOf(player.role());
        if (World.board.network.areLinked(from, destination)) {
            World.board.locations.move(player.role(), destination);
        } else {
            movable = Optional.of(ActionImpossible.DRIVE_CITIES_NOT_LINKED);
        }

        return movable;
    }

}
