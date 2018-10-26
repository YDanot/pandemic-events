package domain.actions.basics;

import domain.actions.Action;
import domain.game.Player;
import domain.infection.Disease;
import domain.network.City;
import domain.network.CityName;
import domain.role.Role;
import domain.treatment.Treatment;
import infra.World;

import java.util.Arrays;

abstract class MovementAction extends Action {

    protected final CityName destination;

    MovementAction(CityName destination) {
        this.destination = destination;
    }

    @Override
    public void act(Player p) {
        move(p);
        if (p.role().equals(Role.MEDIC)) {
            medicSpecialAction();
        }
    }

    public abstract void move(Player p);

    private void medicSpecialAction() {
        City city = World.board.network.get(destination);
        Arrays.stream(Disease.values())
                .filter(d -> !city.isHealthyFor(d) && World.board.cureMarkerArea.hasBeenCured(d))
                .forEach(
                        disease -> new Treatment().fullTreat(city, disease)
                );
    }
}
