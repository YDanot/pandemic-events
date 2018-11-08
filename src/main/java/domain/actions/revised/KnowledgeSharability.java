package domain.actions.revised;

import domain.network.CityName;
import domain.player.cards.PlayerCard;
import domain.player.cards.PlayerHand;
import domain.role.Role;

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

    public boolean sharable() {
        return isInPlaceWithReceiver() && actorHasSharingCardInHand() || actor.equals(Role.RESEARCHER);
    }
}
