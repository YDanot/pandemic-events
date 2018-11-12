package domain.actions.movement;

import domain.actions.ActionImpossible;
import domain.game.Player;
import domain.network.CityName;
import domain.player.cards.PlayerCard;
import domain.player.cards.PlayerHand;
import infra.World;

import java.util.Optional;

public class CharterFly extends MovementAction {

    public CharterFly(CityName destination) {
        super(destination);
    }

    @Override
    public Optional<ActionImpossible> move(Player player) {
        return fly(player, World.game.playerHands.handOf(player));
    }

    public Optional<ActionImpossible> fly(Player actor, PlayerHand playerHand) {
        Optional<ActionImpossible> movable = Optional.empty();
        CityName locationsOfactor = World.board.locations.locationsOf(actor.role());
        PlayerCard playerCard = PlayerCard.valueOf(locationsOfactor.name());
        if (playerHand.contains(playerCard)) {
            World.board.locations.move(actor.role(), destination);
            playerHand.discard(playerCard);
        } else {
            movable = Optional.of(ActionImpossible.CHARTER_BUILD_STATION_ACTOR_DOES_NOT_HAVE_CARD_IN_HAND);
        }
        return movable;
    }
}
