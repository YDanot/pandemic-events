package domain.action.basics;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.actions.basics.*;
import domain.game.Player;
import domain.network.CityName;
import domain.role.Role;
import infra.World;
import org.assertj.core.api.Assertions;

import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;


public class BasicActionsSteps {

    @And("^(.*) is located at (.*)")
    public void isLocatedAt(Role role, CityName cityName) throws Throwable {
        World.board.locations.move(role, cityName);
    }

    @When("^(.*) drives to (.*)$")
    public void drivesTo(Role role, CityName cityName) throws Throwable {
        drive(role, Collections.singletonList(cityName));
    }

    @Then("^(.*) should be able to drive to (.*)$")
    public void shouldBeAbleToMoveTo(Role role, List<CityName> cityNames) throws Throwable {
        drive(role, cityNames);
    }

    @Then("^(.*) should not be able to drive to (.*)")
    public void shouldNotBeAbleToMoveTo(Role role, List<CityName> cityNames) throws Throwable {
        Assertions.assertThatExceptionOfType(ForbiddenMove.class).isThrownBy(() -> drive(role, cityNames));
    }

    @Then("^(.*) should be able to shuttle flight to (.*)$")
    public void shouldBeAbleToFlightTo(Role role, List<CityName> destinations) throws Throwable {
        shuttle(role, destinations);
    }

    @Then("^(.*) should not be able to shuttle flight to (.*)")
    public void shouldNotBeAbleToShuttleTo(Role role, List<CityName> cityNames) throws Throwable {
        Assertions.assertThatExceptionOfType(ForbiddenMove.class).isThrownBy(() -> shuttle(role, cityNames));
    }

    @Then("^(.*) should not be able to direct flight to (.*)")
    public void shouldNotBeAbleToDirectFlightTo(Role role, CityName cityName) throws Throwable {
        Assertions.assertThatExceptionOfType(ForbiddenMove.class).isThrownBy(() -> Player.as(role).take(new DirectFly(cityName)));
    }

    @Then("^(.*) should not be able to charter flight to (.*)$")
    public void scientistShouldNotBeAbleToCharterFlightToNew_York(Role role, CityName cityName) throws Throwable {
        Assertions.assertThatExceptionOfType(ForbiddenMove.class).isThrownBy(() -> Player.as(role).take(new CharterFly(cityName)));
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

    private void drive(Role role, List<CityName> destinations) {
        BiConsumer<Role, CityName> drive = (r, d) -> {
            Assertions.assertThat(World.game.isTurnOf(r)).isTrue();
            Player.as(r).take(new DriveOrFerry(d));
        };
        trip(role, destinations, drive);
    }

    private void shuttle(Role role, List<CityName> destinations) {

        BiConsumer<Role, CityName> shuttleFlight = (r, d) -> {
            Assertions.assertThat(World.game.isTurnOf(r)).isTrue();
            Player.as(r).take(new ShuttleFly(d));
        };
        trip(role, destinations, shuttleFlight);
    }

    private void trip(Role role, List<CityName> destinations, BiConsumer<Role, CityName> conveyance) {
        for (CityName destination : destinations) {
            roundTrip(role, conveyance, destination);
        }
    }

    private void roundTrip(Role role, BiConsumer<Role, CityName> conveyance, CityName destination) {
        CityName from = World.board.locations.locationsOf(role);
        conveyance.accept(role, destination);
        Assertions.assertThat(World.board.locations.locationsOf(role).equals(destination)).isTrue();
        conveyance.accept(role, from);
        Assertions.assertThat(World.board.locations.locationsOf(role).equals(from)).isTrue();
    }

    @Then("^(.*) should be located at (.*)$")
    public void shouldBeLocatedAt(Role role, CityName cityName) throws Throwable {
        Assertions.assertThat(World.board.locations.locationsOf(role)).isEqualTo(cityName);
    }

}
