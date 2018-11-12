package domain.actions.role;

import domain.actions.movement.MovementEvent;
import domain.actions.movement.MovementEventListener;
import domain.infection.Disease;
import domain.treatment.FullTreatment;

import java.util.Arrays;

public class MedicActionOnMovement implements MovementEventListener {

    @Override
    public void onMovement(MovementEvent movementEvent) {
        Arrays.stream(Disease.values())
                .forEach(
                        disease -> new FullTreatment(disease, movementEvent.destination()).treat()
                );
    }
}
