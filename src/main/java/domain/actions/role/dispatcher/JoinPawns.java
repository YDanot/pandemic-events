package domain.actions.role.dispatcher;

import domain.actions.ActionImpossible;
import domain.game.Player;
import domain.network.CityName;
import domain.role.Role;
import infra.World;

import java.util.Optional;

public class JoinPawns extends DispatcherAction {

    private final Role joiner;
    private final Role joined;

    public JoinPawns(Agreement agreement, Role joiner, Role joined) {
        super(agreement);
        this.joiner = joiner;
        this.joined = joined;
    }

    @Override
    public Optional<ActionImpossible> specialAct(Player p) {
        CityName destination = World.board.locations.locationsOf(joined);
        World.board.locations.move(joiner, destination);
        return Optional.empty();
    }

}
