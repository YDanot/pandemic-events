package domain.player.cards;


import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class PlayerCardsPiles {

    private final List<PlayerCard> discard;
    private List<PlayerCard> draw;

    public PlayerCardsPiles() {
        this.draw = new ArrayList<>();
        Arrays.stream(PlayerCard.values()).forEach(c -> {
            if (!c.equals(PlayerCard.EPIDEMIC)) draw.add(c);
        });
        Collections.shuffle(draw);
        this.discard = new ArrayList<>();
    }

    public PlayerCard draw() {
        PlayerCard pop = this.draw.remove(draw.size() - 1);
        discard.add(pop);
        return pop;
    }

    public List<PlayerCard> discardPile() {
        return discard;
    }

    public List<PlayerCard> drawPile() {
        return draw;
    }

    public void addEpidemicCardsToDrawPile(int nbEpidemicCards) {

        Collection<List<PlayerCard>> lists = partitioned(BigDecimal.valueOf(draw.size()).divide(BigDecimal.valueOf(nbEpidemicCards), BigDecimal.ROUND_HALF_UP).intValue());
        draw.clear();
        lists.forEach(deck -> {
            deck.add(PlayerCard.EPIDEMIC);
            Collections.shuffle(deck);
            draw.addAll(deck);
        });

    }

    private Collection<List<PlayerCard>> partitioned(int parts) {

        final AtomicInteger counter = new AtomicInteger(0);

        return draw.stream()
                .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / parts))
                .values();
    }
}
