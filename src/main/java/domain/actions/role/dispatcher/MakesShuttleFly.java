package domain.actions.role.dispatcher;

import domain.actions.ActionImpossible;
import domain.actions.movement.ShuttleFly;
import domain.game.Player;
import domain.network.CityName;
import domain.role.Role;

import java.util.Optional;

public class MakesShuttleFly extends DispatcherAction {

    private final Role actor;
    private final CityName destination;

    public MakesShuttleFly(Agreement agreement, Role actor, CityName destination) {
        super(agreement);
        this.actor = actor;
        this.destination = destination;
    }

    @Override
    public Optional<ActionImpossible> specialAct(Player p) {
        return Player.as(actor).take(new ShuttleFly(destination));
    }

}
