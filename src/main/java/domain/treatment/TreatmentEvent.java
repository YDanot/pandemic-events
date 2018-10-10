package domain.treatment;

import domain.infection.Disease;
import domain.network.CityName;


public class TreatmentEvent {

    public final Disease disease;
    public final CityName cityName;

    public TreatmentEvent(Disease disease, CityName cityName) {
        this.disease = disease;
        this.cityName = cityName;
    }
}
