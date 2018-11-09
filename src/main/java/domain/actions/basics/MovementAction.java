package domain.actions.basics;

import domain.actions.Action;
import domain.game.Player;
import domain.network.CityName;
import infra.World;

abstract class MovementAction extends Action {

    protected final CityName destination;

    MovementAction(CityName destination) {
        this.destination = destination;
    }

    @Override
    public void act(Player p) {
        move(p);
        World.eventBus.publish(new MovementEvent(destination));
    }

    public abstract void move(Player p);

}
