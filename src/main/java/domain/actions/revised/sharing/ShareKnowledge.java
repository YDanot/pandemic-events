package domain.actions.revised.sharing;

import domain.actions.ActionImpossible;
import domain.actions.revised.RevisedAction;
import domain.game.Player;
import domain.network.CityName;
import domain.player.cards.PlayerCard;
import domain.player.cards.PlayerHand;
import infra.World;

import java.util.Optional;

public class ShareKnowledge extends RevisedAction {

    private final Player receiver;
    private final PlayerCard playerCard;

    public ShareKnowledge(Player receiver, PlayerCard playerCard) {
        this.receiver = receiver;
        this.playerCard = playerCard;
    }

    @Override
    public Optional<ActionImpossible> act(Player actor) {

        CityName actorLocation = World.board.locations.locationsOf(actor.role());
        CityName receiverLocation = World.board.locations.locationsOf(receiver.role());
        PlayerHand actorHand = World.game.playerHands.handOf(actor);

        KnowledgeSharability knowledgeSharability = new KnowledgeSharability(actor.role(), actorLocation, receiverLocation, actorHand);
        Optional<ActionImpossible> sharable = knowledgeSharability.sharable();

        if (!sharable.isPresent()) {
            actorHand.pull(playerCard);
            World.game.playerHands.handOf(receiver).deal(playerCard);
        }

        return sharable;
    }
}
