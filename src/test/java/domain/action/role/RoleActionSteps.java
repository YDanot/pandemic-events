package domain.action.role;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.actions.ActionImpossible;
import domain.actions.role.*;
import domain.network.CityName;
import domain.role.Role;
import infra.World;
import org.assertj.core.api.Assertions;

public class RoleActionSteps {

    @When("^He joins (.*) and (.*)$")
    public void dispatcherJoinMedicAndScientist(Role joined, Role joiner) throws Throwable {
        World.game.players.currentTurn().gameplay().take(new JoinPawns(joiner, joined));
    }

    @Then("^he should not be able to joins (.*) and (.*)$")
    public void heShouldNotBeAbleToJoinsDispatcherAndScientist(Role joined, Role joiner) throws Throwable {
        Assertions.assertThatExceptionOfType(ActionImpossible.class).isThrownBy(() -> World.game.players.currentTurn().gameplay().take(new JoinPawns(joiner, joined)));
    }

    @Then("^he should be able to make (.*) drive to (.*)$")
    public void heShouldBeAbleToMakeSomeoneDriveToSomewhere(Role role, CityName cityName) throws Throwable {
        World.game.players.currentTurn().gameplay().take(new MakesDrive(role, cityName));
    }

    @When("^he makes (.*) direct fly to (.*)$")
    public void heMakesScientistDirectFlightToBaghdad(Role role, CityName cityName) throws Throwable {
        World.game.players.currentTurn().gameplay().take(new MakesDirectFly(role, cityName));
    }

    @When("^he makes (.*) charter fly to (.*)$")
    public void heMakesScientistCharterFlightToSydney(Role role, CityName cityName) throws Throwable {
        World.game.players.currentTurn().gameplay().take(new MakesCharterFly(role, cityName));
    }

    @When("^he makes (.*) shuttle fly to (.*)$")
    public void heMakesScientistShuttleFlyToAtlanta(Role role, CityName cityName) throws Throwable {
        World.game.players.currentTurn().gameplay().take(new MakesShuttleFly(role, cityName));
    }
}
