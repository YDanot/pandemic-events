package domain.action.role;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.actions.role.dispatcher.*;
import domain.network.CityName;
import domain.role.Role;
import infra.World;
import org.assertj.core.api.Assertions;

public class DispatcherActionSteps {

    @When("^He joins (.*) and (.*)$")
    public void dispatcherJoinMedicAndScientist(Role joined, Role joiner) throws Throwable {
        World.game.players.currentTurn().gameplay().take(new JoinPawns(agreementOf(joiner), joiner, joined));
    }

    @Then("^he should not be able to joins (.*) and (.*)$")
    public void heShouldNotBeAbleToJoinsDispatcherAndScientist(Role joined, Role joiner) throws Throwable {
        Assertions.assertThat(
                World.game.players.currentTurn().gameplay().take(new JoinPawns(agreementOf(joiner), joiner, joined))).isPresent();
    }

    @Then("^he should be able to make (.*) drive to (.*)$")
    public void heShouldBeAbleToMakeSomeoneDriveToSomewhere(Role role, CityName cityName) throws Throwable {
        World.game.players.currentTurn().gameplay().take(new MakesDrive(agreementOf(role), role, cityName));
    }

    @When("^he makes (.*) direct fly to (.*)$")
    public void heMakesScientistDirectFlightToBaghdad(Role role, CityName cityName) throws Throwable {
        World.game.players.currentTurn().gameplay().take(new MakesDirectFly(agreementOf(role), role, cityName));
    }

    @When("^he makes (.*) charter fly to (.*)$")
    public void heMakesScientistCharterFlightToSydney(Role role, CityName cityName) throws Throwable {
        World.game.players.currentTurn().gameplay().take(new MakesCharterFly(agreementOf(role), role, cityName));
    }

    @When("^he makes (.*) shuttle fly to (.*)$")
    public void heMakesScientistShuttleFlyToAtlanta(Role role, CityName cityName) throws Throwable {
        World.game.players.currentTurn().gameplay().take(new MakesShuttleFly(agreementOf(role), role, cityName));
    }

    private Agreement agreementOf(Role role) {
        return World.game.players.currentTurn().agreementOf(role);
    }

    @And("^(.*) agreed to be moved by Dispatcher$")
    public void agreedToBeMovedByDispatcher(Role role) throws Throwable {
        World.game.players.currentTurn().agreeToBeMovedByDispatcher(role);
    }

    @When("^(.*) disagrees to be moved by Dispatcher$")
    public void disagreedToBeMovedByDispatcher(Role role) throws Throwable {
        World.game.players.currentTurn().disagreeToBeMovedByDispatcher(role);
    }
}
