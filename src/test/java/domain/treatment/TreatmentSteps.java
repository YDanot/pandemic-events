package domain.treatment;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.actions.revised.TreatmentAction;
import domain.game.Player;
import domain.game.PossibleActions;
import domain.infection.Disease;
import domain.role.Role;
import domain.treatment.cure.EradicationEvent;
import infra.World;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.stream.Collectors;


public class TreatmentSteps {

    @And("^(Blue|Black|Red|Yellow) has been eradicated$")
    public void blueHasBeenEradicated(Disease disease) throws Throwable {
        World.eventBus.publish(new EradicationEvent(disease));
    }

    @When("^(.*) treats (.*)$")
    public void treats(Role role, Disease disease) throws Throwable {
        PossibleActions possibleActions = World.game.possibleActions();
        List<Treatment> treatments = possibleActions.treatment();
        treatments.stream().filter(t -> t.disease().equals(disease)).forEach(t ->
                Player.as(role).take(new TreatmentAction(t)));
    }

    @Then("^(?:.*) should not be able to treat (.*)$")
    public void scientistShouldNotBeAbleToTreatBlue(Disease disease) throws Throwable {
        PossibleActions possibleActions = World.game.possibleActions();
        List<Treatment> treatments = possibleActions.treatment();
        Assertions.assertThat(treatments.stream().map(Treatment::disease).collect(Collectors.toList())).doesNotContain(disease);
    }

    @Then("^(?:.*) should be able to treat (Blue|Black|Red|Yellow) disease$")
    public void scientistShouldBeAbleToTreatBlueDisease(Disease disease) throws Throwable {
        Assertions.assertThat(World.game.possibleActions().treatment().stream().map(Treatment::disease).collect(Collectors.toList()))
                .contains(disease);
    }

}

