package domain.treatment.cure;


import domain.infection.Disease;

public class CureDiscoveringEvent {

    public final Disease disease;

    public CureDiscoveringEvent(Disease disease) {
        this.disease = disease;
    }
}
