package pandemic.events;


import pandemic.City;

public class OutbreakPropagator implements OutbreakListener{

    @Override
    public void onOutbreak(OutbreakEvent outbreakEvent) {

        World.get().network.citiesLinkedTo(outbreakEvent.city).forEach(City::infect);

    }
}
