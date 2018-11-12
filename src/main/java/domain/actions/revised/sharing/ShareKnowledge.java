package domain.actions.revised.sharing;

import domain.actions.ActionImpossible;
import domain.actions.revised.RevisedAction;
import domain.game.Player;
import domain.network.CityName;
import domain.player.cards.PlayerCard;
import domain.player.cards.PlayerHand;
import infra.World;

public class ShareKnowledge extends RevisedAction {

    private final Player receiver;
    private final PlayerCard playerCard;

    public ShareKnowledge(Player receiver, PlayerCard playerCard) {
        this.receiver = receiver;
        this.playerCard = playerCard;
    }

    @Override
    public void act(Player actor) {
        CityName actorLocation = World.board.locations.locationsOf(actor.role());
        CityName receiverLocation = World.board.locations.locationsOf(receiver.role());
        PlayerHand actorHand = World.game.playerHands.handOf(actor);

        if (!new KnowledgeSharability(actor.role(), actorLocation, receiverLocation, actorHand).sharable()) {
            throw new ActionImpossible();
        }

        actorHand.pull(playerCard);
        World.game.playerHands.handOf(receiver).deal(playerCard);
    }
}
