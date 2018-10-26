package domain.game;

import domain.network.CityName;
import domain.player.cards.PlayerHand;

public class PossibleActions {

    static PossibleActions NONE = new PossibleActions();

    private final MoveTo moveTo;

    PossibleActions(CityName locationsOfCurrentPlayer, PlayerHand currentPlayerHand) {
        this.moveTo = moves(locationsOfCurrentPlayer, currentPlayerHand);
    }

    private PossibleActions() {
        moveTo = null;
    }

    private MoveTo moves(CityName locationsOfCurrentPlayer, PlayerHand currentPlayerHand) {
        return MoveTo.computePossibleDestinations(locationsOfCurrentPlayer, currentPlayerHand);
    }

    public MoveTo moves() {
        return moveTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PossibleActions)) return false;

        PossibleActions that = (PossibleActions) o;

        return moveTo != null ? moveTo.equals(that.moveTo) : that.moveTo == null;
    }

    @Override
    public int hashCode() {
        return moveTo != null ? moveTo.hashCode() : 0;
    }
}
