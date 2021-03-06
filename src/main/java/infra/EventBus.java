package infra;

import domain.actions.movement.MovementEvent;
import domain.actions.movement.MovementEventListener;
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
import domain.treatment.cure.*;

import java.util.List;

public interface EventBus {

    void listenOutbreak(OutbreakListener listener);

    void listenInfection(InfectionListener listener);

    void listenNoAvailableCubeLeft(NoAvailableCubeLeftListener listener);

    void listenCureDiscovering(CureMarkerArea cureMarkerArea);

    void listenAllDiseasesCured(AllDiseasesCuredListener listener);

    void listenMax(MaxOutbreakNumberReachedListener listener);

    void listenEradication(EradicationListener listener);

    void listenInfectionCardDrawn(InfectionCardDrawnListener infectionCardDrawnListener);

    void listenEpidemic(EpidemicListener infectionCardDrawnListener);

    void listenTakeCube(TakeCubeEventListener
                                takeCubeEventListener);

    List<OutbreakEvent> getOutbreakEvents();

    void listenMovement(MovementEventListener movementEventListener);

    void publish(InfectionEvent infectionEvent);

    void publish(OutbreakEvent outbreakEvent);

    void publish(NoAvailableCubeLeftEvent noAvailableCubeLeftEvent);

    void publish(CureDiscoveringEvent cureDiscoveringEvent);

    void publish(AllDiseaseCuredEvent allDiseaseCuredEvent);

    void publish(MaxOutbreakNumberReachedEvent maxOutbreakNumberReachedEvent);

    void publish(EradicationEvent eradicationEvent);

    void publish(InfectionCardDrawnEvent infectionCardDrawnEvent);

    void publish(TakeCubeEvent takeCubeEvent);

    void publish(EpidemicEvent epidemicEvent);

    List<EpidemicEvent> getEpidemicEvents();

    List<InfectionEvent> getInfectionEvents();

    void publish(MovementEvent movementEvent);
}
