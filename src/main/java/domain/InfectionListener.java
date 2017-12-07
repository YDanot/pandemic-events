package domain;

import domain.events.InfectionEvent;

public interface InfectionListener {

    void onInfection(InfectionEvent infectionEvent);
}
