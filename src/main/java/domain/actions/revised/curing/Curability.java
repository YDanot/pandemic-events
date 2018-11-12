package domain.actions.revised.curing;

import domain.actions.ActionImpossible;
import domain.infection.Disease;
import domain.network.CityName;
import domain.player.cards.PlayerHand;
import domain.player.cards.SubHand;
import domain.role.Role;
import infra.World;

import java.util.Optional;

public class Curability {

    private final Disease disease;
    private final SubHand hand;
    private final CityName location;
    private final Role role;

    public Curability(Disease disease, PlayerHand hand, CityName location, Role role) {
        this.disease = disease;
        this.hand = hand.subHand(hand.get());
        this.location = location;
        this.role = role;
    }

    Curability(Disease disease, SubHand hand, CityName location, Role role) {
        this.disease = disease;
        this.hand = hand;
        this.location = location;
        this.role = role;
    }

    public Optional<ActionImpossible> curable() {
        ActionImpossible cause = null;

        if (!hasEnoughCardOfDiseaseColorInHisHand()) {
            cause = ActionImpossible.CURE_HAS_NOT_ENOUGH_COLOR_CARD;
        }

        if (!researchStationBuiltOnLocationOf()) {
            cause = ActionImpossible.CURE_NO_RESEARCH_STATION_BUILT;
        }

        return Optional.ofNullable(cause);

    }

    private boolean hasEnoughCardOfDiseaseColorInHisHand() {
        return diseaseCardNumberUpperThan(5) ||
                diseaseCardNumberUpperThan(4) && role.equals(Role.SCIENTIST);
    }

    private boolean diseaseCardNumberUpperThan(int number) {
        return hand.countDiseaseCard(disease) >= number;
    }

    private boolean researchStationBuiltOnLocationOf() {
        return World.board.researchStations.builtOn(location);

    }
}
