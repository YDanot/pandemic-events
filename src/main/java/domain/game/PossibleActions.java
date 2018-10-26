package domain.game;

import domain.network.CityName;
import domain.player.cards.PlayerHand;
import infra.World;

public class PossibleActions {

    static PossibleActions NONE = new PossibleActions();

    private final CityName locationsOfCurrentPlayer;
    private final PlayerHand currentPlayerHand;

    PossibleActions(CityName locationsOfCurrentPlayer, PlayerHand currentPlayerHand) {
        this.locationsOfCurrentPlayer = locationsOfCurrentPlayer;
        this.currentPlayerHand = currentPlayerHand;
    }

    private PossibleActions() {
        locationsOfCurrentPlayer = null;
        currentPlayerHand = null;
    }

    private MoveTo moves(CityName locationsOfCurrentPlayer, PlayerHand currentPlayerHand) {
        return MoveTo.computePossibleDestinations(locationsOfCurrentPlayer, currentPlayerHand);
    }

    public MoveTo moves() {
        return MoveTo.computePossibleDestinations(locationsOfCurrentPlayer, currentPlayerHand);
    }

    public boolean buildAResearchStation() {
        return World.board.researchStations.buildableIn(locationsOfCurrentPlayer);
    }

}
