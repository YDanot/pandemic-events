package domain.cube;

import domain.infection.Disease;


public class NoAvailableCubeLeftEvent {

    public final Disease disease;

    public NoAvailableCubeLeftEvent(Disease disease) {
        this.disease = disease;
    }
}
