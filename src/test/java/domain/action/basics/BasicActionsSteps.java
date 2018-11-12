package domain.action.basics;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.actions.movement.CharterFly;
import domain.actions.movement.DirectFly;
import domain.actions.movement.DriveOrFerry;
import domain.game.Player;
import domain.network.CityName;
import domain.role.Role;
import infra.World;
import org.assertj.core.api.Assertions;

import java.util.List;


public class BasicActionsSteps {

    @And("^(.*) is located at (.*)")
    public void isLocatedAt(Role role, CityName cityName) throws Throwable {
        World.board.locations.move(role, cityName);
    }

    @When("^(.*) drives to (.*)$")
    public void drivesTo(Role role, CityName cityName) throws Throwable {
        Assertions.assertThat(World.game.isTurnOf(role)).isTrue();
        Player.as(role).take(new DriveOrFerry(cityName));
    }

    @Then("^(.*) should be able to drive to (.*)$")
    public void shouldBeAbleToMoveTo(Role role, List<CityName> cityNames) throws Throwable {
        Assertions.assertThat(World.game.isTurnOf(role)).isTrue();
        Assertions.assertThat(World.game.possibleActions().movements().drivingDestinations()).containsAll(cityNames);
    }

    @Then("^(.*) should not be able to drive to (.*)")
    public void shouldNotBeAbleToMoveTo(Role role, List<CityName> cityNames) throws Throwable {
        Assertions.assertThat(World.game.isTurnOf(role)).isTrue();
        Assertions.assertThat(World.game.possibleActions().movements().drivingDestinations()).doesNotContainAnyElementsOf(cityNames);
    }

    @Then("^(.*) should be able to shuttle flight to (.*)$")
    public void shouldBeAbleToFlightTo(Role role, List<CityName> destinations) throws Throwable {
        Assertions.assertThat(World.game.isTurnOf(role)).isTrue();
        Assertions.assertThat(World.game.possibleActions().movements().shuttleDestinations()).containsAll(destinations);
    }

    @Then("^(.*) should not be able to shuttle flight to (.*)")
    public void shouldNotBeAbleToShuttleTo(Role role, List<CityName> cityNames) throws Throwable {
        Assertions.assertThat(World.game.isTurnOf(role)).isTrue();
        Assertions.assertThat(World.game.possibleActions().movements().shuttleDestinations()).doesNotContainAnyElementsOf(cityNames);
    }

    @Then("^(.*) should not be able to direct flight to (.*)")
    public void shouldNotBeAbleToDirectFlightTo(Role role, CityName cityName) throws Throwable {
        Assertions.assertThat(World.game.isTurnOf(role)).isTrue();
        Assertions.assertThat(World.game.possibleActions().movements().directFlightDestinations()).doesNotContain(cityName);
    }

    @Then("^(.*) should not be able to charter flight to (.*)$")
    public void scientistShouldNotBeAbleToCharterFlightToNew_York(Role role, CityName cityName) throws Throwable {
        Assertions.assertThat(World.game.isTurnOf(role)).isTrue();
        Assertions.assertThat(World.game.possibleActions().movements().charterFlightDestinations()).doesNotContain(cityName);
    }

    @When("^(.*) direct flies to (.*)$")
    public void scientistDirectFlightToNew_York(Role role, CityName cityName) throws Throwable {
        Assertions.assertThat(World.game.isTurnOf(role)).isTrue();
        Player.as(role).take(new DirectFly(cityName));
    }

    @When("^(.*) charter flies to (.*)$")
    public void charterTo(Role role, CityName cityName) throws Throwable {
        Assertions.assertThat(World.game.isTurnOf(role)).isTrue();
        Player.as(role).take(new CharterFly(cityName));
    }

    @Then("^(.*) should be located at (.*)$")
    public void shouldBeLocatedAt(Role role, CityName cityName) throws Throwable {
        Assertions.assertThat(World.board.locations.locationsOf(role)).isEqualTo(cityName);
    }

}
