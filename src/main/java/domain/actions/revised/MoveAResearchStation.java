package domain.actions.revised;

import domain.game.Player;
import domain.network.CityName;
import domain.player.cards.PlayerCard;
import domain.player.cards.PlayerHand;
import infra.World;

public class MoveAResearchStation extends RevisedAction {

    private CityName from;

    public MoveAResearchStation(CityName from) {
        this.from = from;
    }

    @Override
    public void act(Player p) {
        PlayerHand playerHand = World.game.playerHands.handOf(p);
        CityName location = World.board.locations.locationsOf(p.role());
        PlayerCard locationCard = PlayerCard.valueOf(location.name());

        if (playerHand.contains(locationCard)) {
            World.board.researchStations.move(from, location);
            playerHand.discard(locationCard);
        }
    }
}
