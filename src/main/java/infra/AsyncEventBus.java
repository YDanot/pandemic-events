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
import java.util.concurrent.CompletableFuture;

public class AsyncEventBus implements EventBus {

    private final List<OutbreakEvent> outbreakEvents = new ArrayList<>();
    private final List<EpidemicEvent> epidemicEvents = new ArrayList<>();
    private final List<InfectionEvent> infectionEvents = new ArrayList<>();
    private final List<InfectionListener> infectionListeners = new ArrayList<>();
    private final List<OutbreakListener> outbreakListeners = new ArrayList<>();
    private final List<NoAvailableCubeLeftListener> noAvailableCubeLeftListeners = new ArrayList<>();
    private final List<TreatmentListener> treatmentListeners = new ArrayList<>();
    private final List<CureDiscoveringListener> cureDiscoveringListeners = new ArrayList<>();
    private final List<AllDiseasesCuredListener> allDiseasesCuredListeners = new ArrayList<>();
    private final List<MaxOutbreakNumberReachedListener> maxOutbreakNumberReachedEventListeners = new ArrayList<>();
    private final List<EradicationListener> eradicationListeners = new ArrayList<>();
    private final List<InfectionCardDrawnListener> infectionCardDrawnListeners = new ArrayList<>();
    private final List<TakeCubeEventListener> takeCubeListeners = new ArrayList<>();
    private final List<EpidemicListener> epidemicListeners = new ArrayList<>();

    @Override
    public void listenOutbreak(OutbreakListener listener) {
        outbreakListeners.add(listener);
    }

    @Override
    public void listenInfection(InfectionListener listener) {
        infectionListeners.add(listener);
    }

    @Override
    public void listenNoAvailableCubeLeft(NoAvailableCubeLeftListener listener) {
        noAvailableCubeLeftListeners.add(listener);
    }

    @Override
    public void listenTreatment(TreatmentListener treatmentListener) {
        treatmentListeners.add(treatmentListener);
    }

    @Override
    public void listenCureDiscovering(CureMarkerArea cureMarkerArea) {
        cureDiscoveringListeners.add(cureMarkerArea);
    }

    @Override
    public void listenAllDiseasesCured(AllDiseasesCuredListener listener) {
        allDiseasesCuredListeners.add(listener);
    }

    @Override
    public void listenMax(MaxOutbreakNumberReachedListener listener) {
        maxOutbreakNumberReachedEventListeners.add(listener);
    }

    @Override
    public void listenEradication(EradicationListener listener) {
        eradicationListeners.add(listener);
    }

    @Override
    public void listenInfectionCardDrawn(InfectionCardDrawnListener infectionCardDrawnListener) {
        infectionCardDrawnListeners.add(infectionCardDrawnListener);
    }

    @Override
    public void listenTakeCube(TakeCubeEventListener
                                       takeCubeEventListener) {
        takeCubeListeners.add(takeCubeEventListener);
    }

    @Override
    public void listenEpidemic(EpidemicListener epidemicListener) {
        epidemicListeners.add(epidemicListener);
    }

    //private final List<InfectionAppliedListener> infectionAppliedEventListeners = new ArrayList<>();

//    @Override
//    public void listenInfectionApplied(InfectionAppliedListener listener) {
//        infectionAppliedEventListeners.add(listener);
//    }


    //@Override
    //public void publish(InfectionAppliedEvent infectionAppliedEvent) {
    //    CompletableFuture.runAsync(() -> infectionAppliedEventListeners.forEach(listener -> listener.onInfectionApplied(infectionAppliedEvent)));
    //}

    @Override
    public List<OutbreakEvent> getOutbreakEvents() {
        return outbreakEvents;
    }

    @Override
    public void publish(InfectionEvent infectionEvent) {
        infectionEvents.add(infectionEvent);
        CompletableFuture.runAsync(() -> infectionListeners.forEach(l -> l.onInfection(infectionEvent)));
    }

    @Override
    public void publish(OutbreakEvent outbreakEvent) {
        outbreakEvents.add(outbreakEvent);
        CompletableFuture.runAsync(() -> outbreakListeners.forEach(l -> l.onOutbreak(outbreakEvent)));
    }

    @Override
    public void publish(NoAvailableCubeLeftEvent noAvailableCubeLeftEvent) {
        CompletableFuture.runAsync(() -> noAvailableCubeLeftListeners.forEach(l -> l.onNoAvailableCubeLeft(noAvailableCubeLeftEvent)));
    }

    @Override
    public void publish(TreatmentEvent treatmentEvent) {
        CompletableFuture.runAsync(() -> treatmentListeners.forEach(l -> l.onTreatment(treatmentEvent)));
    }

    @Override
    public void publish(CureDiscoveringEvent cureDiscoveringEvent) {
        CompletableFuture.runAsync(() -> cureDiscoveringListeners.forEach(l -> l.onCureDiscovered(cureDiscoveringEvent)));
    }

    @Override
    public void publish(AllDiseaseCuredEvent allDiseaseCuredEvent) {
        CompletableFuture.runAsync(() -> allDiseasesCuredListeners.forEach(l -> l.onAllDiseasesCured(allDiseaseCuredEvent)));
    }

    @Override
    public void publish(MaxOutbreakNumberReachedEvent maxOutbreakNumberReachedEvent) {
        CompletableFuture.runAsync(() -> maxOutbreakNumberReachedEventListeners.forEach(MaxOutbreakNumberReachedListener::onMaxOutbreakNumberReached));
    }

    @Override
    public void publish(EradicationEvent eradicationEvent) {
        CompletableFuture.runAsync(() -> eradicationListeners.forEach(l -> l.onEradication(eradicationEvent)));
    }

    @Override
    public void publish(InfectionCardDrawnEvent infectionCardDrawnEvent) {
        CompletableFuture.runAsync(() -> infectionCardDrawnListeners.forEach(l -> l.onInfectionCardDrawn(infectionCardDrawnEvent)));
    }

    @Override
    public void publish(TakeCubeEvent takeCubeEvent) {
        CompletableFuture.runAsync(() -> takeCubeListeners.forEach(l -> l.takeCube(takeCubeEvent)));
    }

    @Override
    public void publish(EpidemicEvent epidemicEvent) {
        epidemicEvents.add(epidemicEvent);
        CompletableFuture.runAsync(() -> epidemicListeners.forEach((l) -> l.onEpidemic(epidemicEvent)));
    }

    @Override
    public List<EpidemicEvent> getEpidemicEvents() {
        return epidemicEvents;
    }

    @Override
    public List<InfectionEvent> getInfectionEvents() {
        return infectionEvents;
    }
}