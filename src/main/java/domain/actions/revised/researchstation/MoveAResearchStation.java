package domain.actions.revised.researchstation;

import domain.actions.ActionImpossible;
import domain.actions.revised.RevisedAction;
import domain.game.Player;
import domain.network.CityName;
import domain.player.cards.PlayerCard;
import domain.player.cards.PlayerHand;
import infra.World;

import java.util.Optional;

public class MoveAResearchStation extends RevisedAction {

    private CityName from;

    public MoveAResearchStation(CityName from) {
        this.from = from;
    }

    @Override
    public Optional<ActionImpossible> act(Player p) {
        PlayerHand playerHand = World.game.playerHands.handOf(p);
        CityName location = World.board.locations.locationsOf(p.role());
        PlayerCard locationCard = PlayerCard.valueOf(location.name());

        Buildability buildability = new Buildability(playerHand, location);

        Optional<ActionImpossible> buildable = buildability.buildable();

        if (!buildable.isPresent()) {
            World.board.researchStations.move(from, location);
            playerHand.discard(locationCard);
        }


        return buildable;
    }
}
