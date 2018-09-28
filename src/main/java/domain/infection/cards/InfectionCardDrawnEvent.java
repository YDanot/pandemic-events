package domain.infection.cards;

public class InfectionCardDrawnEvent {

    private final InfectionCard card;

    public InfectionCardDrawnEvent(InfectionCard card) {
        this.card = card;
    }

    public InfectionCard card() {
        return card;
    }
}
