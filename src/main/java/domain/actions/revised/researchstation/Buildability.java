package domain.actions.revised.researchstation;

import domain.actions.ActionImpossible;
import domain.network.CityName;
import domain.player.cards.PlayerCard;
import domain.player.cards.PlayerHand;
import infra.World;

import java.util.Optional;

public class Buildability {

    private final PlayerHand playerHand;
    private final CityName location;

    public Buildability(PlayerHand playerHand, CityName location) {
        this.playerHand = playerHand;
        this.location = location;
    }

    public Optional<ActionImpossible> buildable() {
        ActionImpossible cause = null;

        if (!playerHand.contains(PlayerCard.valueOf(location.name()))) {
            cause = ActionImpossible.BUILD_STATION_ACTOR_DOES_NOT_HAVE_CARD_IN_HAND;
        }

        if (World.board.researchStations.builtOn(location)) {
            cause = ActionImpossible.BUILD_STATION_ALREADY_BUILT;
        }

        return Optional.ofNullable(cause);
    }
}
