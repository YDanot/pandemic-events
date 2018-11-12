package domain.actions.revised.researchstation;

import domain.actions.ActionImpossible;
import domain.actions.revised.RevisedAction;
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

        Buildability buildability = new Buildability(playerHand, location);

        if (!buildability.buildable()) {
            throw new ActionImpossible("You cannot build a research station in " + location);
        }

        World.board.researchStations.buildOn(location);
        playerHand.discard(locationCard);
    }
}
