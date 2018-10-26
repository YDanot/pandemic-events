package domain.actions.revised;

import domain.game.Player;
import domain.network.CityName;
import domain.player.cards.PlayerCard;
import domain.player.cards.PlayerHand;
import infra.World;

public class BuildAResearchStation extends RevisedAction {

    @Override
    public void act(Player player) {
        PlayerHand playerHand = World.game.playerHands.handOf(player);
        CityName location = World.board.locations.locationsOf(player.role());
        PlayerCard locationCard = PlayerCard.valueOf(location.name());
        if (playerHand.contains(locationCard)) {
            World.board.researchStations.buildOn(location);
            playerHand.discard(locationCard);
        }
    }
}
