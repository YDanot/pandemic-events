package domain.cube;

import domain.infection.Disease;

public class TakeCubeEvent {

    public final Disease disease;

    public TakeCubeEvent(Disease disease) {
        this.disease = disease;
    }
}
