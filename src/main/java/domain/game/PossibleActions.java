package domain.game;

import domain.actions.revised.Curability;
import domain.infection.Disease;
import domain.network.CityName;
import domain.player.cards.PlayerHand;
import infra.World;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Disease> curable() {
        return Arrays.stream(Disease.values()).filter(d ->
                new Curability(d, currentPlayerHand, locationsOfCurrentPlayer).curable()).collect(Collectors.toList());
    }
}
