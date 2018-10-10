package domain.player.cards;


import domain.epidemic.EpidemicEvent;
import infra.World;

import java.util.*;

public class PlayerCardsPiles {

    private final List<PlayerCard> discard;
    private Stack<PlayerCard> draw;

    public PlayerCardsPiles() {
        this.draw = new Stack<>();
        Arrays.stream(PlayerCard.values()).forEach(c -> {
            if (!c.equals(PlayerCard.EPIDEMIC)) draw.add(c);
        });
        Collections.shuffle(draw);
        this.discard = new ArrayList<>();
    }

    public PlayerCard draw() {
        PlayerCard card = this.draw.remove(draw.size() - 1);
        if (card.equals(PlayerCard.EPIDEMIC)) {
            World.eventBus.publish(new EpidemicEvent());
        }
        return card;
    }

    public List<PlayerCard> discardPile() {
        return discard;
    }

    public Stack<PlayerCard> drawPile() {
        return draw;
    }

    public void discard(PlayerCard playerCard) {
        discardPile().add(playerCard);
    }
}
