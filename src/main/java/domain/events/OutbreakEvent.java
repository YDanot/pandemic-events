package domain.events;


import domain.CityName;

public class OutbreakEvent {

    public final CityName cityName;

    public OutbreakEvent(CityName cityName) {
        this.cityName = cityName;
    }
}
