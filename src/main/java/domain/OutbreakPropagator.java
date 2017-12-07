package domain;


import domain.events.OutbreakEvent;
import infra.World;

public class OutbreakPropagator implements OutbreakListener{

    @Override
    public void onOutbreak(OutbreakEvent outbreakEvent) {
        World.getNetwork().citiesLinkedTo(outbreakEvent.cityName).forEach(Disease.BLUE::infect);
    }
}
