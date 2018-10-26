package domain.actions.revised;

import domain.game.Player;
import domain.infection.Disease;
import domain.player.cards.PlayerHand;
import domain.player.cards.SubHand;
import domain.treatment.cure.CureDiscoveringEvent;
import infra.World;

public class CureDisease extends RevisedAction {

    private final Disease disease;
    private final SubHand subHand;

    public CureDisease(Disease disease, SubHand subHand) {
        this.disease = disease;
        this.subHand = subHand;
    }

    @Override
    public void act(Player player) {
        PlayerHand playerHand = World.game.playerHands.handOf(player);
        Curability curability = new Curability(disease, subHand, World.board.locations.locationsOf(player.role()));
        if (!curability.curable()) {
            throw new IllegalArgumentException("You cannot curable " + disease + " disease");
        }

        subHand.cards().forEach(playerHand::discard);
        World.eventBus.publish(new CureDiscoveringEvent(disease));
    }


}
