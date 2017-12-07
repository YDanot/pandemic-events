package domain.infection;

import domain.events.InfectionEvent;

public interface InfectionListener {

    void onInfection(InfectionEvent infectionEvent);
}
