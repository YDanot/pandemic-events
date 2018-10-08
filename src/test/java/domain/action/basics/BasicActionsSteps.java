package domain.action.basics;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.actions.basics.ForbiddenMoveException;
import domain.network.CityName;
import domain.role.Role;
import infra.World;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.function.BiConsumer;


public class BasicActionsSteps {

    @And("^(.*) is located at (.*)")
    public void isLocatedAt(Role role, CityName cityName) throws Throwable {
        World.game.locations.move(role, cityName);
    }

    @Then("^(.*) should be able to drive to (.*)$")
    public void shouldBeAbleToMoveTo(Role role, List<CityName> cityNames) throws Throwable {
        drive(role, cityNames);
    }

    @Then("^(.*) should not be able to drive to (.*)")
    public void shouldNotBeAbleToMoveTo(Role role, List<CityName> cityNames) throws Throwable {
        Assertions.assertThatExceptionOfType(ForbiddenMoveException.class).isThrownBy(() -> drive(role, cityNames));
    }

    @Then("^(.*) should be able to shuttle flight to (.*)$")
    public void shouldBeAbleToFlightTo(Role role, List<CityName> destinations) throws Throwable {
        shuttle(role, destinations);
    }

    @Then("^(.*) should not be able to shuttle flight to (.*)")
    public void shouldNotBeAbleToShuttleTo(Role role, List<CityName> cityNames) throws Throwable {
        Assertions.assertThatExceptionOfType(ForbiddenMoveException.class).isThrownBy(() -> shuttle(role, cityNames));
    }

    @Then("^(.*) should not be able to direct flight to (.*)")
    public void shouldNotBeAbleToDirectFlightTo(Role role, CityName cityName) throws Throwable {
        Assertions.assertThatExceptionOfType(ForbiddenMoveException.class).isThrownBy(() -> World.game.locations.directFlight(role, cityName));
    }

    @Then("^(.*) should not be able to charter flight to (.*)$")
    public void scientistShouldNotBeAbleToCharterFlightToNew_York(Role role, CityName cityName) throws Throwable {
        Assertions.assertThatExceptionOfType(ForbiddenMoveException.class).isThrownBy(() -> World.game.locations.charterFlight(role, cityName));
    }

    private void drive(Role role, List<CityName> destinations) {
        BiConsumer<Role, CityName> drive = (Role r, CityName d) -> World.game.locations.drive(r, d);
        trip(role, destinations, drive);
    }

    private void shuttle(Role role, List<CityName> destinations) {
        BiConsumer<Role, CityName> shuttleFlight = (r, d) -> World.game.locations.shuttleFlight(r, d);
        trip(role, destinations, shuttleFlight);
    }

    private void trip(Role role, List<CityName> destinations, BiConsumer<Role, CityName> conveyance) {
        for (CityName destination : destinations) {
            roundTrip(role, conveyance, destination);
        }
    }

    private void roundTrip(Role role, BiConsumer<Role, CityName> conveyance, CityName destination) {
        CityName from = World.game.locations.locationsOf(role);
        conveyance.accept(role, destination);
        Assertions.assertThat(World.game.locations.locationsOf(role).equals(destination)).isTrue();
        conveyance.accept(role, from);
        Assertions.assertThat(World.game.locations.locationsOf(role).equals(from)).isTrue();
    }

    @When("^(.*) direct flies to (.*)$")
    public void scientistDirectFlightToNew_York(Role role, CityName cityName) throws Throwable {
        World.game.locations.directFlight(role, cityName);
    }

    @Then("^(.*) should be located at (.*)$")
    public void shouldBeLocatedAt(Role role, CityName cityName) throws Throwable {
        Assertions.assertThat(World.game.locations.locationsOf(role)).isEqualTo(cityName);
    }

    @When("^(.*) charter flies to (.*)$")
    public void charterTo(Role role, CityName cityName) throws Throwable {
        World.game.locations.charterFlight(role, cityName);
    }

}
