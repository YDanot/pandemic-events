package domain.actions.basics;

import domain.game.Player;
import domain.network.CityName;
import domain.player.cards.PlayerCard;
import domain.player.cards.PlayerHand;
import infra.World;

public class CharterFly extends BasicAction {
    
    private CityName destination;

    public CharterFly(CityName destination) {
        this.destination = destination;
    }

    @Override
    public void act(Player player) {
        fly(player, World.game.playerHands.handOf(player));
    }

    public void fly(Player actor, PlayerHand playerHand) {
        PlayerCard playerCard = PlayerCard.valueOf(World.board.locations.locationsOf(actor.role()).name());
        if (playerHand.contains(playerCard)) {
            World.board.locations.move(actor.role(), destination);
            playerHand.discard(playerCard);
        } else {
            throw new ForbiddenMove(actor.role(), destination);
        }
    }
}
