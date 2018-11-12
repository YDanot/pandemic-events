package domain.game;

import domain.actions.revised.curing.Curability;
import domain.actions.revised.sharing.KnowledgeSharability;
import domain.actions.revised.treatment.Treatability;
import domain.infection.Disease;
import domain.network.CityName;
import domain.player.cards.PlayerCard;
import domain.player.cards.PlayerHand;
import domain.role.Role;
import domain.treatment.BasicTreatment;
import domain.treatment.FullTreatment;
import domain.treatment.Treatment;
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
                new KnowledgeSharability(role, locationsOfCurrentPlayer, World.board.locations.locationsOf(p.role()), currentPlayerHand).sharable()
        );
    }

    public List<Treatment> treatment() {

        return Arrays.stream(Disease.values()).filter(disease ->
                new Treatability(locationsOfCurrentPlayer, disease).treatable())
                .map((Disease d) -> {
                    if (Role.MEDIC.equals(role) || World.board.cureMarkerArea.hasBeenCured(d)) {
                        return new FullTreatment(d, locationsOfCurrentPlayer);
                    }
                    return new BasicTreatment(d, locationsOfCurrentPlayer);
                })
                .collect(Collectors.toList());
    }

    public List<CityName> moveAResearchStation() {
        if (currentPlayerHand != null && locationsOfCurrentPlayer != null &&
                currentPlayerHand.contains(PlayerCard.valueOf(locationsOfCurrentPlayer.name()))) {
            return new ArrayList<>(World.board.researchStations.locations);
        }
        return Collections.emptyList();
    }

    public boolean shareWithMe() {
        return locationsOfCurrentPlayer != null && locationsOfCurrentPlayer.equals(World.board.locations.locationsOf(Role.RESEARCHER));
    }
}
