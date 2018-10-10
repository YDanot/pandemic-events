package domain.actions.basics;

import domain.game.Player;
import domain.network.CityName;
import domain.player.cards.PlayerCard;
import domain.player.cards.PlayerHand;
import infra.World;

public class DirectFly implements BasicAction {

    private CityName destination;

    public DirectFly(CityName destination) {
        this.destination = destination;
    }

    @Override
    public void accept(Player player) {
        PlayerCard playerCard = PlayerCard.valueOf(destination.name());
        PlayerHand playerHand = World.game.playerHands.handOf(player);
        if (playerHand.contains(playerCard)) {
            World.game.locations.move(player.role(), destination);
            playerHand.discard(playerCard);
        } else {
            throw new ForbiddenMove(World.game.locations.locationsOf(player.role()), destination);
        }
    }
}
