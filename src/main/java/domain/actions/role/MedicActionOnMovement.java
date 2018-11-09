package domain.actions.role;

import domain.actions.basics.MovementEvent;
import domain.actions.basics.MovementEventListener;
import domain.infection.Disease;
import domain.network.City;
import domain.treatment.Treatment;
import infra.World;

import java.util.Arrays;

public class MedicActionOnMovement implements MovementEventListener {

    @Override
    public void onMovement(MovementEvent movementEvent) {
        City city = World.board.network.get(movementEvent.destination());
        Arrays.stream(Disease.values())
                .filter(d -> !city.isHealthyFor(d) && World.board.cureMarkerArea.hasBeenCured(d))
                .forEach(
                        disease -> new Treatment().fullTreat(city, disease)
                );
    }
}
