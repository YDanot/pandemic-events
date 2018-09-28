package infra;

import domain.cube.NoAvailableCubeLeftEvent;
import domain.cube.NoAvailableCubeLeftListener;
import domain.infection.InfectionEvent;
import domain.infection.InfectionListener;
import domain.infection.cards.InfectionCardDrawnEvent;
import domain.infection.outbreak.MaxOutbreakNumberReachedEvent;
import domain.infection.outbreak.MaxOutbreakNumberReachedListener;
import domain.infection.outbreak.OutbreakEvent;
import domain.infection.outbreak.OutbreakListener;
import domain.treatment.TreatmentEvent;
import domain.treatment.TreatmentListener;
import domain.treatment.cure.*;

import java.util.List;

public interface EventBus {

    void listenOutbreak(OutbreakListener listener);

    void listenInfection(InfectionListener listener);

    void listenNoAvailableCubeLeft(NoAvailableCubeLeftListener listener);

    void listenTreatment(TreatmentListener treatmentListener);

    void listenCureDiscovering(CureMarkerArea cureMarkerArea);

    void listenAllDiseasesCured(AllDiseasesCuredListener listener);

    void listenMax(MaxOutbreakNumberReachedListener listener);

    void listenEradication(EradicationListener listener);

    void listenInfectionCardDrawn(InfectionCardDrawnListener infectionCardDrawnListener);

    List<OutbreakEvent> getOutbreakEvents();

    void publish(InfectionEvent infectionEvent);

    void publish(OutbreakEvent outbreakEvent);

    void publish(NoAvailableCubeLeftEvent noAvailableCubeLeftEvent);

    void publish(TreatmentEvent treatmentEvent);

    void publish(CureDiscoveringEvent cureDiscoveringEvent);

    void publish(AllDiseaseCuredEvent allDiseaseCuredEvent);

    void publish(MaxOutbreakNumberReachedEvent maxOutbreakNumberReachedEvent);

    void publish(EradicationEvent eradicationEvent);

    void publish(InfectionCardDrawnEvent infectionCardDrawnEvent);
}
