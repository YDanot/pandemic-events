package domain.actions.role.dispatcher;

import domain.actions.basics.ShuttleFly;
import domain.game.Player;
import domain.network.CityName;
import domain.role.Role;

public class MakesShuttleFly extends DispatcherAction {

    private final Role actor;
    private final CityName destination;

    public MakesShuttleFly(Agreement agreement, Role actor, CityName destination) {
        super(agreement);
        this.actor = actor;
        this.destination = destination;
    }

    @Override
    public void specialAct(Player p) {
        Player.as(actor).take(new ShuttleFly(destination));
    }

}
