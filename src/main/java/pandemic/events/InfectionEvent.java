package pandemic.events;


import pandemic.City;
import pandemic.InfectionLevel;

public class InfectionEvent {

    public final City city;
    public final InfectionLevel originalLevel;

    public InfectionEvent(City city) {
        this.city = city;
        originalLevel = city.level;
    }
}
