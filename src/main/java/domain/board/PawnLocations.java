package domain.board;

import domain.actions.basics.ForbiddenMove;
import domain.game.Player;
import domain.game.Players;
import domain.network.CityName;
import domain.player.cards.PlayerCard;
import domain.player.cards.PlayerHand;
import domain.role.Role;
import infra.World;

import java.util.HashMap;
import java.util.Map;

public class PawnLocations {

    private final Map<Role, CityName> locations = new HashMap<>();

    public PawnLocations(CityName cityName, Players players) {
        this(cityName, players.get());
    }

    private PawnLocations(CityName cityName, Iterable<Player> roles) {
        for (Player player : roles) {
            locations.put(player.role(), cityName);
        }
    }

    public CityName locationsOf(Role role) {
        return locations.get(role);
    }

    public void move(Role role, CityName cityName) {
        locations.put(role, cityName);
    }

    public void directFlight(Role role, CityName destination) {
        PlayerCard playerCard = PlayerCard.valueOf(destination.name());
        PlayerHand playerHand = World.game.playerHands.handOf(Player.as(role));
        if (playerHand.contains(playerCard)) {
            locations.put(role, destination);
            playerHand.discard(playerCard);
        } else {
            throw new ForbiddenMove(locationsOf(role), destination);
        }
    }

    public void charterFlight(Role role, CityName destination) {
        PlayerCard playerCard = PlayerCard.valueOf(locationsOf(role).name());
        PlayerHand playerHand = World.game.playerHands.handOf(Player.as(role));
        if (playerHand.contains(playerCard)) {
            locations.put(role, destination);
            playerHand.discard(playerCard);
        } else {
            throw new ForbiddenMove(locationsOf(role), destination);
        }
    }
}
