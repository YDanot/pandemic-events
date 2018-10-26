package domain.game;

import domain.network.CityName;
import domain.player.cards.PlayerCard;
import domain.player.cards.PlayerHand;
import infra.World;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class MoveTo extends PossibleAction {

    private final List<CityName> drivingDestinations;
    private final Set<CityName> shuttleDestinations;
    private final List<CityName> directFlightDestinations;
    private final List<CityName> charterFlightDestinations;

    private MoveTo(List<CityName> drivingDestinations,
                   Set<CityName> shuttleDestinations,
                   List<CityName> directFlightDestinations,
                   List<CityName> charterFlightDestinations) {
        this.drivingDestinations = drivingDestinations;
        this.shuttleDestinations = shuttleDestinations;
        this.directFlightDestinations = directFlightDestinations;
        this.charterFlightDestinations = charterFlightDestinations;
    }

    static MoveTo computePossibleDestinations(CityName locationsOfCurrentPlayer, PlayerHand currentPlayerHand) {
        return new MoveTo(
                computeDrivingDestinations(locationsOfCurrentPlayer),
                computeShuttleDestinations(locationsOfCurrentPlayer),
                computeDirectFlightDestinations(locationsOfCurrentPlayer, currentPlayerHand),
                computeCharterDestinations(locationsOfCurrentPlayer, currentPlayerHand));
    }

    private static List<CityName> computeCharterDestinations(CityName locationsOfCurrentPlayer, PlayerHand currentPlayerHand) {
        List<CityName> cityNames;
        if (currentPlayerHand.contains(PlayerCard.valueOf(locationsOfCurrentPlayer.name()))) {
            cityNames = new ArrayList<>(Arrays.asList(CityName.values()));
            cityNames.remove(locationsOfCurrentPlayer);
        } else {
            cityNames = emptyList();
        }
        return cityNames;
    }

    private static List<CityName> computeDirectFlightDestinations(CityName locationsOfCurrentPlayer, PlayerHand currentPlayerHand) {
        return currentPlayerHand.get().stream().map((c) -> CityName.valueOf(c.name())).filter(c -> !c.equals(locationsOfCurrentPlayer)).collect(toList());
    }

    private static List<CityName> computeDrivingDestinations(CityName locationsOfCurrentPlayer) {
        return World.board.network.citiesLinkedTo(locationsOfCurrentPlayer);
    }

    private static Set<CityName> computeShuttleDestinations(CityName locationsOfCurrentPlayer) {
        if (World.board.researchStations.builtOn(locationsOfCurrentPlayer)) {
            Set<CityName> locations = new HashSet<>(World.board.researchStations.locations);
            locations.remove(locationsOfCurrentPlayer);
            return locations;
        }
        return Collections.emptySet();
    }

    public List<CityName> drivingDestinations() {
        return drivingDestinations;
    }

    public Set<CityName> shuttleDestinations() {
        return shuttleDestinations;
    }

    public List<CityName> directFlightDestinations() {
        return directFlightDestinations;
    }

    public List<CityName> charterFlightDestinations() {
        return charterFlightDestinations;
    }

    public Set<CityName> destinations() {
        Stream<CityName> cityNameStream = Stream.of(drivingDestinations, shuttleDestinations, directFlightDestinations, charterFlightDestinations)
                .flatMap(Collection::stream);
        return cityNameStream.collect(toSet());
    }
}
