package domain.events;


import domain.City;
import domain.CityName;
import domain.Disease;
import domain.InfectionLevel;

import static domain.Disease.BLUE;

public class InfectionEvent {

    public final CityName cityName;
    public final Disease disease;
    public final InfectionLevel originalLevel;

    public InfectionEvent(City city) {
        this.cityName = city.name();
        this.originalLevel = city.level();
        this.disease = BLUE;
    }
}
