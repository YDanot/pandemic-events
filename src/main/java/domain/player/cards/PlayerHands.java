package domain.player.cards;

import domain.game.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PlayerHands {

    private final Map<Player, PlayerHand> hands = new HashMap<>();

    private PlayerHands() {
    }

    public static PlayerHands build(Iterable<Player> players) {
        PlayerHands playerHands = new PlayerHands();
        players.forEach(r -> playerHands.hands.put(r, new PlayerHand()));
        return playerHands;
    }

    public void deal(Player player, PlayerCard... playerCards) {
        PlayerHand playerHand = hands.computeIfAbsent(player, k -> new PlayerHand());
        Arrays.stream(playerCards).forEach(playerHand::deal);
    }

    public PlayerHand handOf(Player player) {
        return hands.get(player);
    }
}
