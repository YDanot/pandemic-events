package domain.actions.movement;

import domain.game.Player;
import domain.network.CityName;
import domain.player.cards.PlayerCard;
import domain.player.cards.PlayerHand;
import infra.World;

public class CharterFly extends MovementAction {

    public CharterFly(CityName destination) {
        super(destination);
    }

    @Override
    public void move(Player player) {
        fly(player, World.game.playerHands.handOf(player));
    }

    public void fly(Player actor, PlayerHand playerHand) {
        CityName locationsOfactor = World.board.locations.locationsOf(actor.role());
        PlayerCard playerCard = PlayerCard.valueOf(locationsOfactor.name());
        if (playerHand.contains(playerCard)) {
            World.board.locations.move(actor.role(), destination);
            playerHand.discard(playerCard);
        } else {
            throw new ForbiddenMove(actor.role(), destination);
        }
    }
}
