package domain.actions.revised.curing;

import domain.actions.ActionImpossible;
import domain.actions.revised.RevisedAction;
import domain.game.Player;
import domain.infection.Disease;
import domain.player.cards.PlayerHand;
import domain.player.cards.SubHand;
import domain.treatment.cure.CureDiscoveringEvent;
import infra.World;

import java.util.Optional;

public class CureDisease extends RevisedAction {

    private final Disease disease;
    private final SubHand subHand;

    public CureDisease(Disease disease, SubHand subHand) {
        this.disease = disease;
        this.subHand = subHand;
    }

    @Override
    public Optional<ActionImpossible> act(Player player) {
        PlayerHand playerHand = World.game.playerHands.handOf(player);
        Curability curability = new Curability(disease, subHand, World.board.locations.locationsOf(player.role()), player.role());
        Optional<ActionImpossible> curable = curability.curable();
        if (!curable.isPresent()) {
            subHand.cards().forEach(playerHand::discard);
            World.eventBus.publish(new CureDiscoveringEvent(disease));
        }

        return curable;
    }


}
