package domain.actions.revised.researchstation;

import domain.network.CityName;
import domain.player.cards.PlayerCard;
import domain.player.cards.PlayerHand;
import infra.World;

class Buildability {

    private final PlayerHand playerHand;
    private final CityName location;

    Buildability(PlayerHand playerHand, CityName location) {
        this.playerHand = playerHand;
        this.location = location;
    }

    boolean buildable() {
        return playerHand.contains(PlayerCard.valueOf(location.name())) && !World.board.researchStations.builtOn(location);
    }
}
