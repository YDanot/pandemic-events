package domain;

import domain.events.InfectionEvent;
import domain.events.OutbreakEvent;
import infra.World;

public class OutbreakDetector implements InfectionListener{

    @Override
    public void onInfection(InfectionEvent infectionEvent) {

        if (infectionEvent.originalLevel.maxReached()){
            World.publish(new OutbreakEvent(infectionEvent.cityName));
        }
    }
}
