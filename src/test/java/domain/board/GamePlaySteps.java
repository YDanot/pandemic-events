package domain.board;

import cucumber.api.java.en.Then;
import domain.network.CityName;
import infra.World;
import org.assertj.core.api.Assertions;

import java.util.*;

public class GamePlaySteps {

    @Then("^possible driving destination of (?:.*) should be (.*)$")
    public void drive(List<CityName> expected) throws Throwable {
        List<CityName> actual = World.game.possibleActions().movements().drivingDestinations();
        hasSameElements(actual, expected);
    }

    @Then("^possible shuttle destination of (?:.*) should be (.*)$")
    public void shuttle(List<CityName> expected) throws Throwable {
        List<CityName> actual = new ArrayList<>(World.game.possibleActions().movements().shuttleDestinations());
        hasSameElements(actual, expected);
    }

    @Then("^possible direct flight destination of (?:.*) should be (.*)$")
    public void direct(List<CityName> expected) throws Throwable {
        List<CityName> actual = World.game.possibleActions().movements().directFlightDestinations();
        hasSameElements(actual, expected);
    }

    @Then("^possible charter flight destination of (?:.*) should be all cities$")
    public void charter() throws Throwable {
        List<CityName> actual = World.game.possibleActions().movements().charterFlightDestinations();
        List<CityName> expected = new ArrayList<>(Arrays.asList(CityName.values()));
        expected.remove(World.board.locations.locationsOf(World.game.players.currentTurn().player().role()));
        hasSameElements(actual, expected);
    }

    private void hasSameElements(List<CityName> actual, List<CityName> expected) {
        Collections.sort(actual);
        Collections.sort(expected);
        Assertions.assertThat(actual).containsExactlyElementsOf(expected);
    }

    @Then("^possible movement of (?:.*) should be (.*)$")
    public void movements(List<CityName> expected) throws Throwable {
        Set<CityName> destinations = World.game.possibleActions().movements().destinations();
        hasSameElements(new ArrayList<>(destinations), expected);
    }
}
