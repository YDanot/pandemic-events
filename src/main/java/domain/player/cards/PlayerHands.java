package domain.player.cards;

import domain.role.Role;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PlayerHands {

    private final Map<Role, PlayerHand> hands = new HashMap<>();

    public void build(Iterable<Role> roles) {
        roles.forEach(r -> hands.put(r, new PlayerHand()));
    }

    public void deal(Role role, PlayerCard... playerCards) {
        PlayerHand playerHand = hands.computeIfAbsent(role, k -> new PlayerHand());
        Arrays.stream(playerCards).forEach(playerHand::deal);
    }

    public PlayerHand handOf(Role role) {
        return hands.get(role);
    }
}
