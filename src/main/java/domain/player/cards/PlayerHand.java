package domain.player.cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerHand {

    private final Map<PlayerCardColor, List<PlayerCard>> playerCards = new HashMap<>();

    public void deal(PlayerCard playerCard) {

        List<PlayerCard> cards = playerCards.computeIfAbsent(playerCard.color(), k -> new ArrayList<PlayerCard>());
        cards.add(playerCard);
    }

    public List<PlayerCard> get() {
        return playerCards.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

}
