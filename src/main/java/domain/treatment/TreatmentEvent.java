package domain.treatment;

import domain.board.CityName;
import domain.infection.Disease;


public class TreatmentEvent {

    public final Disease disease;
    public final CityName cityName;

    TreatmentEvent(Disease disease, CityName cityName) {
        this.disease = disease;
        this.cityName = cityName;
    }
}
