package domain.actions.role.researcher;

import domain.actions.Action;
import domain.actions.ActionImpossible;
import domain.game.Player;
import domain.network.CityName;
import domain.player.cards.PlayerCard;
import domain.player.cards.PlayerHand;
import domain.role.Role;
import infra.World;

import java.util.Optional;

public class ShareWithMe extends Action {

    private final PlayerCard playerCard;

    public ShareWithMe(PlayerCard playerCard) {
        this.playerCard = playerCard;
    }

    @Override
    public Optional<ActionImpossible> act(Player actor) {
        Optional<ActionImpossible> sharable = Optional.empty();

        CityName actorLocation = World.board.locations.locationsOf(actor.role());
        CityName researcherLocation = World.board.locations.locationsOf(Role.RESEARCHER);
        PlayerHand researcherHand = World.game.playerHands.handOf(Player.as(Role.RESEARCHER));
        if (!actorLocation.equals(researcherLocation)) {
            return Optional.of(ActionImpossible.SHARE_KNOWLEDGE_ARE_NOT_IN_THE_SAME_PLACE);
        }
        researcherHand.pull(playerCard);
        World.game.playerHands.handOf(actor).deal(playerCard);
        return sharable;
    }
}
