package domain.game;

import domain.actions.revised.Curability;
import domain.actions.revised.KnowledgeSharability;
import domain.actions.revised.Treatability;
import domain.infection.Disease;
import domain.network.CityName;
import domain.player.cards.PlayerCard;
import domain.player.cards.PlayerHand;
import domain.role.Role;
import infra.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PossibleActions {

    static PossibleActions NONE = new PossibleActions();

    private final CityName locationsOfCurrentPlayer;
    private final PlayerHand currentPlayerHand;
    private final Role role;

    PossibleActions(CityName locationsOfCurrentPlayer, PlayerHand currentPlayerHand, Role role) {
        this.locationsOfCurrentPlayer = locationsOfCurrentPlayer;
        this.currentPlayerHand = currentPlayerHand;
        this.role = role;
    }

    private PossibleActions() {
        locationsOfCurrentPlayer = null;
        currentPlayerHand = null;
        role = null;
    }

    public Movement movements() {
        return Movement.computePossibleDestinations(locationsOfCurrentPlayer, currentPlayerHand);
    }

    public boolean buildAResearchStation() {
        return currentPlayerHand != null && locationsOfCurrentPlayer != null &&
                currentPlayerHand.contains(PlayerCard.valueOf(locationsOfCurrentPlayer.name())) &&
                World.board.researchStations.buildableIn(locationsOfCurrentPlayer);
    }

    public List<Disease> cure() {
        return Arrays.stream(Disease.values()).filter(disease ->
                new Curability(disease, currentPlayerHand, locationsOfCurrentPlayer, role).curable()).collect(Collectors.toList());
    }

    public boolean share() {
        return World.game.players.get().stream().anyMatch(p ->
                new KnowledgeSharability(locationsOfCurrentPlayer, World.board.locations.locationsOf(p.role()), currentPlayerHand).sharable()
        );
    }

    public List<Disease> treatment() {
        return Arrays.stream(Disease.values()).filter(disease ->
                new Treatability(locationsOfCurrentPlayer, disease).treatable()).collect(Collectors.toList());
    }

    public List<CityName> moveAResearchStation() {
        if (currentPlayerHand != null && locationsOfCurrentPlayer != null &&
                currentPlayerHand.contains(PlayerCard.valueOf(locationsOfCurrentPlayer.name()))) {
            return new ArrayList<>(World.board.researchStations.locations);
        }
        return Collections.emptyList();
    }
}
