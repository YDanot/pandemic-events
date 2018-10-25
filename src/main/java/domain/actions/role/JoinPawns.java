package domain.actions.role;

import domain.game.Player;
import domain.network.CityName;
import domain.role.Role;
import infra.World;

public class JoinPawns extends DispatcherAction {

    private final Role joiner;
    private final Role joined;

    public JoinPawns(Role joiner, Role joined) {
        this.joiner = joiner;
        this.joined = joined;
    }

    @Override
    public void specialAct(Player p) {
        CityName destination = World.board.locations.locationsOf(joined);
        World.board.locations.move(joiner, destination);
    }

}
