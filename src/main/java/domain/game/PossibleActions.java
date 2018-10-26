package domain.game;

import domain.actions.revised.Curability;
import domain.actions.revised.KnowledgeSharability;
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

    public Movement movements() {
        return Movement.computePossibleDestinations(locationsOfCurrentPlayer, currentPlayerHand);
    }

    public boolean buildAResearchStation() {
        return World.board.researchStations.buildableIn(locationsOfCurrentPlayer);
    }

    public List<Disease> cure() {
        return Arrays.stream(Disease.values()).filter(disease ->
                new Curability(disease, currentPlayerHand, locationsOfCurrentPlayer).curable()).collect(Collectors.toList());
    }

    public boolean share() {
        return World.game.players.get().stream().anyMatch(p ->
                new KnowledgeSharability(locationsOfCurrentPlayer, World.board.locations.locationsOf(p.role()), currentPlayerHand).sharable()
        );
    }
}
