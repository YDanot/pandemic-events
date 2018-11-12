package domain.actions.revised.treatment;

import domain.infection.Disease;
import domain.network.CityName;
import infra.World;

public class Treatability {

    private final CityName locationOfActor;
    private final Disease disease;

    public Treatability(CityName locationOfActor, Disease disease) {
        this.locationOfActor = locationOfActor;
        this.disease = disease;
    }

    public boolean treatable() {
        return !World.board.network.get(locationOfActor).infectionLevelFor(disease).isZero();
    }
}
