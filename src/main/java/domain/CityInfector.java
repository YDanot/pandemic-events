package domain;

import domain.events.InfectionEvent;

public class CityInfector implements InfectionListener{

    @Override
    public void onInfection(InfectionEvent infectionEvent) {
        Disease.BLUE.infect(infectionEvent.cityName);
    }
}
