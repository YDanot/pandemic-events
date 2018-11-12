package domain.actions.movement;

import domain.actions.Action;
import domain.actions.ActionImpossible;
import domain.game.Player;
import domain.network.CityName;
import infra.World;

import java.util.Optional;

abstract class MovementAction extends Action {

    protected final CityName destination;

    MovementAction(CityName destination) {
        this.destination = destination;
    }

    @Override
    public Optional<ActionImpossible> act(Player p) {
        Optional<ActionImpossible> move = move(p);
        World.eventBus.publish(new MovementEvent(destination));
        return move;
    }

    public abstract Optional<ActionImpossible> move(Player p);

}
