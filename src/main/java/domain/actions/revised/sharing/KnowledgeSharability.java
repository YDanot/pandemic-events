package domain.actions.revised.sharing;

import domain.actions.ActionImpossible;
import domain.network.CityName;
import domain.player.cards.PlayerCard;
import domain.player.cards.PlayerHand;
import domain.role.Role;

import java.util.Optional;

public class KnowledgeSharability {

    private final CityName actorLocation;
    private final CityName receiverLocation;
    private final PlayerHand actorHand;
    private final Role actor;

    public KnowledgeSharability(Role actor, CityName actorLocation, CityName receiverLocation, PlayerHand actorHand) {
        this.actor = actor;
        this.actorLocation = actorLocation;
        this.receiverLocation = receiverLocation;
        this.actorHand = actorHand;
    }

    private boolean isInPlaceWithReceiver() {
        return actorLocation.equals(receiverLocation);
    }

    private boolean actorHasSharingCardInHand() {
        return actorHand.contains(PlayerCard.valueOf(actorLocation.name()));
    }

    public Optional<ActionImpossible> sharable() {
        if (!isInPlaceWithReceiver()) {
            return Optional.of(ActionImpossible.SHARE_KNOWLEDGE_ARE_NOT_IN_THE_SAME_PLACE);
        }

        if (!actorHasSharingCardInHand() && !actor.equals(Role.RESEARCHER)) {
            return Optional.of(ActionImpossible.SHARE_KNOWLEDGE_ACTOR_DOES_NOT_HAVE_CARD_IN_HAND);
        }

        return Optional.empty();
    }
}
