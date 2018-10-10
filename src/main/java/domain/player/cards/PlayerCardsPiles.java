package domain.player.cards;


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
        return this.draw.remove(draw.size() - 1);
    }

    public List<PlayerCard> discardPile() {
        return discard;
    }

    public Stack<PlayerCard> drawPile() {
        return draw;
    }

    void addToDiscard(PlayerCard playerCard) {
        discardPile().add(playerCard);
    }
}
