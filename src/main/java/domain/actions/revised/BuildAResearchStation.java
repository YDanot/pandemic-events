package domain.actions.revised;

import domain.game.Player;
import infra.World;

public class BuildAResearchStation implements RevisedAction {

    @Override
    public void accept(Player player) {
        World.game.researchStations.buildOn(World.game.locations.locationsOf(player.role()));
    }
}
