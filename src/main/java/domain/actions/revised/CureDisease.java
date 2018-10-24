package domain.actions.revised;

import domain.game.Player;
import domain.infection.Disease;
import domain.player.cards.PlayerHand;
import domain.player.cards.SubHand;
import domain.treatment.cure.CureDiscoveringEvent;
import infra.World;

public class CureDisease implements RevisedAction {

    private final Disease disease;
    private final SubHand subHand;

    public CureDisease(Disease disease, SubHand subHand) {
        this.disease = disease;
        this.subHand = subHand;
    }

    @Override
    public void act(Player player) {
        PlayerHand playerHand = World.game.playerHands.handOf(player);
        if (noResearchStationBuiltOnLocationOf(player) || countDiseaseCardLowerThanFive(disease, subHand)) {
            throw new IllegalArgumentException("You cannot cure " + disease + " disease");
        }

        subHand.cards().forEach(playerHand::discard);
        World.eventBus.publish(new CureDiscoveringEvent(disease));
    }

    private boolean countDiseaseCardLowerThanFive(Disease disease, SubHand subHand) {
        return subHand.countDiseaseCard(disease) < 5;
    }

    private boolean noResearchStationBuiltOnLocationOf(Player player) {
        return !World.board.researchStations.builtOn(World.board.locations.locationsOf(player.role()));
    }
}
