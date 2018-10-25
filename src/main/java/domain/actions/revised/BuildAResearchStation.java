package domain.actions.revised;

import domain.game.Player;
import infra.World;

public class BuildAResearchStation extends RevisedAction {

    @Override
    public void act(Player player) {
        World.board.researchStations.buildOn(World.board.locations.locationsOf(player.role()));
    }
}
