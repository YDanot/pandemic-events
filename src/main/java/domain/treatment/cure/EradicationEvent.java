package domain.treatment.cure;

import domain.infection.Disease;


public class EradicationEvent {

    public Disease disease;

    public EradicationEvent(Disease disease) {
        this.disease = disease;
    }
}
