package infra;


import domain.infection.InfectionEvent;
import domain.infection.outbreak.OutbreakEvent;
import domain.infection.InfectionListener;
import domain.infection.outbreak.OutbreakListener;

import java.util.ArrayList;
import java.util.List;

public class EventBus {

    private final List<InfectionEvent> infectionEvents = new ArrayList<>();
    private final List<InfectionListener> infectionListeners = new ArrayList<>();

    private final List<OutbreakEvent> outbreakEvents = new ArrayList<>();
    private final List<OutbreakListener> outbreakListeners = new ArrayList<>();

    void listenOutbreak(OutbreakListener listener){
        outbreakListeners.add(listener);
    }

    void listenInfection(InfectionListener listener){
        infectionListeners.add(listener);
    }

    public void publish(InfectionEvent infectionEvent){
        infectionEvents.add(infectionEvent);
        for (InfectionListener infectionListener : infectionListeners) {
            infectionListener.onInfection(infectionEvent);
        }
    }

    public void publish(OutbreakEvent outbreakEvent){
        outbreakEvents.add(outbreakEvent);
        for (OutbreakListener outbreakListener : outbreakListeners) {
            outbreakListener.onOutbreak(outbreakEvent);
        }
    }
}
