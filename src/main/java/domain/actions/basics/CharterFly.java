package domain.actions.basics;

import domain.game.Player;
import domain.network.CityName;
import domain.player.cards.PlayerCard;
import domain.player.cards.PlayerHand;
import infra.World;

public class CharterFly implements BasicAction {

    private CityName destination;

    public CharterFly(CityName destination) {
        this.destination = destination;
    }

    @Override
    public void accept(Player player) {
        PlayerCard playerCard = PlayerCard.valueOf(World.board.locations.locationsOf(player.role()).name());
        PlayerHand playerHand = World.game.playerHands.handOf(player);
        if (playerHand.contains(playerCard)) {
            World.board.locations.move(player.role(), destination);
            playerHand.discard(playerCard);
        } else {
            throw new ForbiddenMove(World.board.locations.locationsOf(player.role()), destination);
        }
    }
}
