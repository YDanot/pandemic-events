package domain.actions.revised;

import domain.network.CityName;
import domain.player.cards.PlayerCard;
import domain.player.cards.PlayerHand;

public class KnowledgeSharability {

    private final CityName actorLocation;
    private final CityName receiverLocation;
    private final PlayerHand actorHand;

    public KnowledgeSharability(CityName actorLocation, CityName receiverLocation, PlayerHand actorHand) {
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
        return isInPlaceWithReceiver() && actorHasSharingCardInHand();
    }
}
