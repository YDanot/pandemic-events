package domain.player.cards;

import infra.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerHand {

    private final Map<PlayerCardColor, List<PlayerCard>> hands = new HashMap<>();

    public void deal(PlayerCard playerCard) {

        List<PlayerCard> cards = hands.computeIfAbsent(playerCard.color(), k -> new ArrayList<>());
        cards.add(playerCard);
    }

    public List<PlayerCard> get() {
        return hands.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.valueOf(get());
    }

    public boolean contains(PlayerCard playerCard) {
        return get().contains(playerCard);
    }

    public void discard(PlayerCard playerCard) {
        World.board.playerCardsPiles.discard(playerCard);
        hands.get(playerCard.color()).remove(playerCard);
    }

    private boolean containsAll(List<PlayerCard> discard) {
        return get().containsAll(discard);
    }

    public SubHand subHand(List<PlayerCard> cards) {
        if (!containsAll(cards)) {
            throw new IllegalArgumentException(cards + " is not a sub hand of " + get());
        }
        return new SubHand(cards);
    }

    public void pull(PlayerCard playerCard) {
        hands.get(playerCard.color()).remove(playerCard);
    }

}
