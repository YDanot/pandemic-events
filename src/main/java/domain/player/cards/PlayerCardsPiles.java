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
        PlayerCard pop = this.draw.remove(draw.size() - 1);
        discard.add(pop);
        return pop;
    }

    public List<PlayerCard> discardPile() {
        return discard;
    }

    public Stack<PlayerCard> drawPile() {
        return draw;
    }

    public void addEpidemicCardsToDrawPile(int nbEpidemicCards) {
        divideDrawPileIntoEqualParts(nbEpidemicCards).forEach(this::addRandomlyAnEpidemicCard);
    }

    private void addRandomlyAnEpidemicCard(List<PlayerCard> deck) {
        deck.add(new Random().nextInt(deck.size()), PlayerCard.EPIDEMIC);
        putBackIntoDrawPile(deck);
    }

    private void putBackIntoDrawPile(List<PlayerCard> partition) {
        draw.addAll(partition);
    }

    private List<List<PlayerCard>> divideDrawPileIntoEqualParts(int nbEpidemicCards) {
        List<List<PlayerCard>> lists = splitInto(draw, nbEpidemicCards, new ArrayList<>());
        draw.clear();
        return lists;
    }

    private List<List<PlayerCard>> splitInto(List<PlayerCard> list, int numberOfParts, List<List<PlayerCard>> result) {
        if (numberOfParts == 0) {
            return result;
        }
        int ceil = (int) Math.ceil(((double) list.size()) / numberOfParts);
        result.add(new ArrayList<>(list.subList(0, ceil)));
        return splitInto(new ArrayList<>(list.subList(ceil, list.size())), numberOfParts - 1, result);
    }
}
