package domain.infection.cards;


import infra.World;

import java.util.*;

public class InfectionCardsPiles {

    private final Stack<InfectionCard> draw;
    private final List<InfectionCard> discard;

    public InfectionCardsPiles() {
        this.draw = new Stack<>();
        Arrays.stream(InfectionCard.values()).forEach(this.draw::push);
        Collections.shuffle(draw);
        this.discard = new ArrayList<>();
    }

    public InfectionCard draw() {
        InfectionCard pop = this.draw.pop();
        discard.add(pop);
        World.eventBus.publish(new InfectionCardDrawnEvent(pop));
        return pop;
    }

    public List<InfectionCard> discardPile() {
        return discard;
    }

    public Stack<InfectionCard> drawPile() {
        return draw;
    }

    public InfectionCard drawBottom() {
        InfectionCard pop = this.draw.lastElement();
        draw.remove(pop);
        discard.add(pop);
        return pop;
    }
}
