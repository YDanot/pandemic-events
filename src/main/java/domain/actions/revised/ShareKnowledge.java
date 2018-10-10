package domain.actions.revised;

import domain.game.Player;
import domain.network.CityName;
import domain.player.cards.PlayerCard;
import infra.World;

public class ShareKnowledge {

    private final Player doer;
    private final Player receiver;
    private final PlayerCard playerCard;

    public ShareKnowledge(Player doer, Player receiver, PlayerCard playerCard) {
        this.doer = doer;
        this.receiver = receiver;
        this.playerCard = playerCard;
        if (doerDoesNotHaveSharingCardInHand() || doerAndReceiverAreNotInPlace()) {
            throw new ActionImpossible();
        }
    }

    public void execute() {
        World.game.playerHands.handOf(doer).pull(playerCard);
        World.game.playerHands.handOf(receiver).deal(playerCard);
    }

    private boolean doerAndReceiverAreNotInPlace() {
        CityName doerLocation = World.game.locations.locationsOf(doer.role());
        CityName receiverLocation = World.game.locations.locationsOf(receiver.role());
        CityName place = CityName.valueOf(playerCard.name());
        return !receiverLocation.equals(place) && doerLocation.equals(place);
    }

    private boolean doerDoesNotHaveSharingCardInHand() {
        return !World.game.playerHands.handOf(doer).contains(playerCard);
    }
}
