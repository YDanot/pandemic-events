package domain.player.cards;


import java.util.*;

public class PlayerCardsPiles {

    private final Stack<PlayerCard> draw;
    private final List<PlayerCard> discard;

    public PlayerCardsPiles() {
        this.draw = new Stack<>();
        Arrays.stream(PlayerCard.values()).forEach(this.draw::push);
        Collections.shuffle(draw);
        this.discard = new ArrayList<>();
    }

    public PlayerCard draw() {
        PlayerCard pop = this.draw.pop();
        discard.add(pop);
        return pop;
    }

    public List<PlayerCard> discardPile() {
        return discard;
    }

    public Stack<PlayerCard> drawPile() {
        return draw;
    }

}
