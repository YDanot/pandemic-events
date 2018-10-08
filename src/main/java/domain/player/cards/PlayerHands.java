package domain.player.cards;

import domain.game.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PlayerHands {

    private final Map<Player, PlayerHand> hands = new HashMap<>();

    public void build(Iterable<Player> players) {
        players.forEach(r -> hands.put(r, new PlayerHand()));
    }

    public void deal(Player player, PlayerCard... playerCards) {
        PlayerHand playerHand = hands.computeIfAbsent(player, k -> new PlayerHand());
        Arrays.stream(playerCards).forEach(playerHand::deal);
    }

    public PlayerHand handOf(Player player) {
        return hands.get(player);
    }
}
