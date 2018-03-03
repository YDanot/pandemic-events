package infra;


import domain.cube.NoAvailableCubeLeftEvent;
import domain.cube.NoAvailableCubeLeftListener;
import domain.infection.InfectionEvent;
import domain.infection.InfectionListener;
import domain.infection.outbreak.OutbreakEvent;
import domain.infection.outbreak.OutbreakListener;

import java.util.ArrayList;
import java.util.List;

public class EventBus {

    final List<OutbreakEvent> outbreakEvents = new ArrayList<>();
    private final List<InfectionEvent> infectionEvents = new ArrayList<>();
    private final List<InfectionListener> infectionListeners = new ArrayList<>();
    private final List<OutbreakListener> outbreakListeners = new ArrayList<>();
    private final List<NoAvailableCubeLeftListener> noAvailableCubeLeftListeners = new ArrayList<>();
    NoAvailableCubeLeftEvent noAvailableCubeLeftEvent;

    void listenOutbreak(OutbreakListener listener){
        outbreakListeners.add(listener);
    }

    void listenInfection(InfectionListener listener){
        infectionListeners.add(listener);
    }

    void listenNoAvailableCubeLeft(NoAvailableCubeLeftListener listener) {
        noAvailableCubeLeftListeners.add(listener);
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

    public void publish(NoAvailableCubeLeftEvent noAvailableCubeLeftEvent) {
        this.noAvailableCubeLeftEvent = noAvailableCubeLeftEvent;
        noAvailableCubeLeftListeners.forEach(l -> l.onNoAvailableCubeLeft(noAvailableCubeLeftEvent));
    }
}
