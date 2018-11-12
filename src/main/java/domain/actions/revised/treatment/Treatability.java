package domain.actions.revised.treatment;

import domain.actions.ActionImpossible;
import domain.infection.Disease;
import domain.network.CityName;
import infra.World;

import java.util.Optional;

public class Treatability {

    private final CityName locationOfActor;
    private final Disease disease;

    public Treatability(CityName locationOfActor, Disease disease) {
        this.locationOfActor = locationOfActor;
        this.disease = disease;
    }

    public Optional<ActionImpossible> treatable() {
        ActionImpossible cause = null;

        if (World.board.network.get(locationOfActor).isHealthyFor(disease)) {
            cause = ActionImpossible.TREATMENT_HEALTHY_CITY;
        }

        return Optional.ofNullable(cause);

    }
}
