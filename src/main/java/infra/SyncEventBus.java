package infra;


import domain.cube.NoAvailableCubeLeftEvent;
import domain.cube.NoAvailableCubeLeftListener;
import domain.cube.TakeCubeEvent;
import domain.cube.TakeCubeEventListener;
import domain.epidemic.EpidemicEvent;
import domain.epidemic.EpidemicListener;
import domain.infection.InfectionEvent;
import domain.infection.InfectionListener;
import domain.infection.cards.InfectionCardDrawnEvent;
import domain.infection.cards.InfectionCardDrawnListener;
import domain.infection.outbreak.MaxOutbreakNumberReachedEvent;
import domain.infection.outbreak.MaxOutbreakNumberReachedListener;
import domain.infection.outbreak.OutbreakEvent;
import domain.infection.outbreak.OutbreakListener;
import domain.treatment.TreatmentEvent;
import domain.treatment.TreatmentListener;
import domain.treatment.cure.*;

import java.util.ArrayList;
import java.util.List;

public class SyncEventBus implements EventBus {

    private final List<OutbreakEvent> outbreakEvents = new ArrayList<>();
    private final List<InfectionEvent> infectionEvents = new ArrayList<>();
    private final List<InfectionListener> infectionListeners = new ArrayList<>();
    private final List<OutbreakListener> outbreakListeners = new ArrayList<>();
    private final List<NoAvailableCubeLeftListener> noAvailableCubeLeftListeners = new ArrayList<>();
    private final List<TreatmentListener> treatmentListeners = new ArrayList<>();
    private final List<CureDiscoveringListener> cureDiscoveringListeners = new ArrayList<>();
    private final List<AllDiseasesCuredListener> allDiseasesCuredListeners = new ArrayList<>();
    private List<MaxOutbreakNumberReachedListener> maxOutbreakNumberReachedEventListeners = new ArrayList<>();
    private List<EradicationListener> eradicationListeners = new ArrayList<>();
    private List<InfectionCardDrawnListener> infectionCardDrawnListeners = new ArrayList<>();
    private List<TakeCubeEventListener> takeCubeListeners = new ArrayList<>();
    private List<EpidemicListener> epidemicListeners = new ArrayList<>();

    public void listenOutbreak(OutbreakListener listener) {
        outbreakListeners.add(listener);
    }

    public void listenInfection(InfectionListener listener) {
        infectionListeners.add(listener);
    }

    public void listenNoAvailableCubeLeft(NoAvailableCubeLeftListener listener) {
        noAvailableCubeLeftListeners.add(listener);
    }

    public void listenTreatment(TreatmentListener treatmentListener) {
        treatmentListeners.add(treatmentListener);
    }

    public void listenCureDiscovering(CureMarkerArea cureMarkerArea) {
        cureDiscoveringListeners.add(cureMarkerArea);
    }

    public void listenAllDiseasesCured(AllDiseasesCuredListener listener) {
        allDiseasesCuredListeners.add(listener);
    }

    public void listenMax(MaxOutbreakNumberReachedListener listener) {
        maxOutbreakNumberReachedEventListeners.add(listener);
    }

    public void listenEradication(EradicationListener listener) {
        eradicationListeners.add(listener);
    }

    public void listenInfectionCardDrawn(InfectionCardDrawnListener infectionCardDrawnListener) {
        infectionCardDrawnListeners.add(infectionCardDrawnListener);
    }

    @Override
    public void listenEpidemic(EpidemicListener epidemicListener) {
        epidemicListeners.add(epidemicListener);
    }

    @Override
    public void listenTakeCube(TakeCubeEventListener takeCubeEventListener) {
        takeCubeListeners.add(takeCubeEventListener);
    }

    public void publish(InfectionEvent infectionEvent) {
        infectionEvents.add(infectionEvent);
        for (InfectionListener infectionListener : infectionListeners) {
            infectionListener.onInfection(infectionEvent);
        }
    }

    public void publish(OutbreakEvent outbreakEvent) {
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

    public void publish(MaxOutbreakNumberReachedEvent maxOutbreakNumberReachedEvent) {
        maxOutbreakNumberReachedEventListeners.forEach(MaxOutbreakNumberReachedListener::onMaxOutbreakNumberReached);
    }

    public void publish(EradicationEvent eradicationEvent) {
        eradicationListeners.forEach(l -> l.onEradication(eradicationEvent));
    }

    public void publish(InfectionCardDrawnEvent infectionCardDrawnEvent) {
        infectionCardDrawnListeners.forEach(l -> l.onInfectionCardDrawn(infectionCardDrawnEvent));
    }

    @Override
    public List<OutbreakEvent> getOutbreakEvents() {
        return outbreakEvents;
    }

    @Override
    public void publish(TakeCubeEvent takeCubeEvent) {
        takeCubeListeners.forEach(l -> l.takeCube(takeCubeEvent));
    }

    @Override
    public void publish(EpidemicEvent epidemicEvent) {
        epidemicListeners.forEach(EpidemicListener::onEpidemic);
    }
}
