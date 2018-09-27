package domain.cure;


import domain.infection.Disease;

public class CureDiscoveringEvent {

    public final Disease disease;

    CureDiscoveringEvent(Disease disease) {
        this.disease = disease;
    }
}
