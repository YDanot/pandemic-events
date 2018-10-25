package domain.actions.basics;

import domain.game.Player;
import domain.network.CityName;
import domain.player.cards.PlayerCard;
import domain.player.cards.PlayerHand;
import infra.World;

public class DirectFly extends BasicAction {

    private CityName destination;

    public DirectFly(CityName destination) {
        this.destination = destination;
    }

    @Override
    public void act(Player player) {
        fly(player, World.game.playerHands.handOf(player));
    }

    public void fly(Player player, PlayerHand playerHand) {
        PlayerCard playerCard = PlayerCard.valueOf(destination.name());
        if (playerHand.contains(playerCard)) {
            World.board.locations.move(player.role(), destination);
            playerHand.discard(playerCard);
        } else {
            throw new ForbiddenMove(player.role(), destination);
        }
    }
}
