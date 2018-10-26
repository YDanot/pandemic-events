package domain.actions.revised;

import domain.infection.Disease;
import domain.network.CityName;
import domain.player.cards.PlayerHand;
import domain.player.cards.SubHand;
import infra.World;

public class Curability {

    private final Disease disease;
    private final SubHand hand;
    private final CityName location;

    public Curability(Disease disease, PlayerHand hand, CityName location) {
        this.disease = disease;
        this.hand = hand.subHand(hand.get());
        this.location = location;
    }

    Curability(Disease disease, SubHand hand, CityName location) {
        this.disease = disease;
        this.hand = hand;
        this.location = location;
    }


    public boolean curable() {
        return diseaseCardNumberUpperThanFive() && researchStationBuiltOnLocationOf();
    }

    private boolean diseaseCardNumberUpperThanFive() {
        return hand.countDiseaseCard(disease) >= 5;
    }

    private boolean researchStationBuiltOnLocationOf() {
        return World.board.researchStations.builtOn(location);

    }
}
