package domain.action.role;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.actions.ActionImpossible;
import domain.actions.role.JoinPawns;
import domain.role.Role;
import infra.World;
import org.assertj.core.api.Assertions;

public class RoleActionSteps {

    @When("^He joins (.*) and (.*)$")
    public void dispatcherJoinMedicAndScientist(Role joined, Role joiner) throws Throwable {
        World.game.players.currentTurn().take(new JoinPawns(joiner, joined));
    }

    @Then("^he should not be able to joins (.*) and (.*)$")
    public void heShouldNotBeAbleToJoinsDispatcherAndScientist(Role joined, Role joiner) throws Throwable {
        Assertions.assertThatExceptionOfType(ActionImpossible.class).isThrownBy(() ->
                World.game.players.currentTurn().take(new JoinPawns(joiner, joined)))
                .withMessage("Only the Dispatcher can join pawns");
    }
}
