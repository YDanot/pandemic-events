package domain.actions.movement;

import domain.actions.ActionImpossible;
import domain.game.Player;
import domain.network.CityName;
import domain.player.cards.PlayerCard;
import domain.player.cards.PlayerHand;
import infra.World;

import java.util.Optional;

public class DirectFly extends MovementAction {

    public DirectFly(CityName destination) {
        super(destination);
    }

    @Override
    public Optional<ActionImpossible> move(Player player) {
        return fly(player, World.game.playerHands.handOf(player));
    }

    public Optional<ActionImpossible> fly(Player player, PlayerHand playerHand) {
        Optional<ActionImpossible> movable = Optional.empty();
        PlayerCard playerCard = PlayerCard.valueOf(destination.name());
        if (playerHand.contains(playerCard)) {
            World.board.locations.move(player.role(), destination);
            playerHand.discard(playerCard);
        } else {
            movable = Optional.of(ActionImpossible.DIRECTFLY_BUILD_STATION_ACTOR_DOES_NOT_HAVE_CARD_IN_HAND);
        }
        return movable;
    }
}
