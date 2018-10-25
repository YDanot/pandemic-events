package domain.actions.revised;

import domain.actions.ActionImpossible;
import domain.game.Player;
import domain.network.CityName;
import domain.player.cards.PlayerCard;
import infra.World;

public class ShareKnowledge extends RevisedAction {

    private final Player receiver;
    private final PlayerCard playerCard;

    public ShareKnowledge(Player receiver, PlayerCard playerCard) {
        this.receiver = receiver;
        this.playerCard = playerCard;
    }

    private boolean isNotInPlaceWithReceiver(Player doer) {
        CityName doerLocation = World.board.locations.locationsOf(doer.role());
        CityName receiverLocation = World.board.locations.locationsOf(receiver.role());
        CityName place = CityName.valueOf(playerCard.name());
        return !receiverLocation.equals(place) && doerLocation.equals(place);
    }

    private boolean doerDoesNotHaveSharingCardInHand(Player doer) {
        return !World.game.playerHands.handOf(doer).contains(playerCard);
    }

    @Override
    public void act(Player player) {
        if (doerDoesNotHaveSharingCardInHand(player) || isNotInPlaceWithReceiver(player)) {
            throw new ActionImpossible();
        }
        World.game.playerHands.handOf(player).pull(playerCard);
        World.game.playerHands.handOf(receiver).deal(playerCard);
    }
}
