package infra;


import domain.cube.NoAvailableCubeLeftEvent;
import domain.cube.NoAvailableCubeLeftListener;
import domain.cure.*;
import domain.game.Game;
import domain.infection.InfectionEvent;
import domain.infection.InfectionListener;
import domain.infection.outbreak.OutbreakEvent;
import domain.infection.outbreak.OutbreakListener;
import domain.treatment.Treatment;
import domain.treatment.TreatmentEvent;
import domain.treatment.TreatmentListener;

import java.util.ArrayList;
import java.util.List;

public class EventBus {

    final List<OutbreakEvent> outbreakEvents = new ArrayList<>();
    private final List<InfectionEvent> infectionEvents = new ArrayList<>();
    private final List<InfectionListener> infectionListeners = new ArrayList<>();
    private final List<OutbreakListener> outbreakListeners = new ArrayList<>();
    private final List<NoAvailableCubeLeftListener> noAvailableCubeLeftListeners = new ArrayList<>();
    private final List<TreatmentListener> treatmentListeners = new ArrayList<>();
    private final List<CureDiscoveringListener> cureDiscoveringListeners = new ArrayList<>();
    private final List<AllDiseasesCuredListener> allDiseasesCuredListeners = new ArrayList<>();

    void listenOutbreak(OutbreakListener listener){
        outbreakListeners.add(listener);
    }

    void listenInfection(InfectionListener listener){
        infectionListeners.add(listener);
    }

    void listenNoAvailableCubeLeft(NoAvailableCubeLeftListener listener) {
        noAvailableCubeLeftListeners.add(listener);
    }

    void listenTreatment(TreatmentListener treatmentListener) {
        treatmentListeners.add(treatmentListener);
    }

    void listenCureDiscovering(CureMarkerArea cureMarkerArea) {
        cureDiscoveringListeners.add(cureMarkerArea);
    }

    void listenAllDiseasesCured(AllDiseasesCuredListener listener) {
        allDiseasesCuredListeners.add(listener);
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
        noAvailableCubeLeftListeners.forEach(l -> l.onNoAvailableCubeLeft(noAvailableCubeLeftEvent));
    }

    public void publish(TreatmentEvent treatmentEvent) {
        treatmentListeners.forEach(l -> l.onTreatment(treatmentEvent));
    }

    public void publish(CureDiscoveringEvent cureDiscoveringEvent) {
        cureDiscoveringListeners.forEach(l -> l.onCureDiscovered(cureDiscoveringEvent));
    }

    public void publish(AllDiseaseCuredEvent allDiseaseCuredEvent) {
        allDiseasesCuredListeners.forEach(l -> l.onAllDiseasesCured(allDiseaseCuredEvent));
    }
}
