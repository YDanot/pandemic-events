package domain.actions.role;

import domain.actions.ActionImpossible;
import domain.game.Player;
import domain.network.CityName;
import domain.role.Role;
import infra.World;

public class JoinPawns implements RoleAction {

    private final Role joiner;
    private final Role joined;

    public JoinPawns(Role joiner, Role joined) {
        this.joiner = joiner;
        this.joined = joined;
    }

    @Override
    public void act(Player p) {
        if (!p.role().equals(role())) {
            throw new ActionImpossible("Only the Dispatcher can join pawns");
        }
        CityName destination = World.board.locations.locationsOf(joined);
        World.board.locations.move(joiner, destination);
    }

    @Override
    public Role role() {
        return Role.DISPATCHER;
    }
}
