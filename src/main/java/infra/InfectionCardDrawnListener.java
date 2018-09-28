package infra;

import domain.infection.cards.InfectionCardDrawnEvent;

public interface InfectionCardDrawnListener {

    void onInfectionCardDrawn(InfectionCardDrawnEvent infectionCardDrawnEvent);
}
