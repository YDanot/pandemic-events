package domain.actions.basics;

import domain.network.CityName;

public class MovementEvent {

    private final CityName destination;

    MovementEvent(CityName destination) {
        this.destination = destination;
    }

    public CityName destination() {
        return destination;
    }
}
