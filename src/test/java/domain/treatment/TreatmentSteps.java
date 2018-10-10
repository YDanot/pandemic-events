package domain.treatment;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.actions.ActionImpossible;
import domain.actions.revised.TreatmentOf;
import domain.game.Player;
import domain.infection.Disease;
import domain.network.CityName;
import domain.role.Role;
import domain.treatment.cure.EradicationEvent;
import infra.World;
import org.assertj.core.api.Assertions;


public class TreatmentSteps {

    @When("^(.*) is treated in (.*)$")
    public void isTreatedIn(Disease disease, CityName cityName) throws Throwable {
        Player anyPlayer = World.game.players.get().iterator().next();
        World.game.locations.move(anyPlayer.role(), cityName);
        anyPlayer.act(new TreatmentOf(disease));
    }

    @And("^(Blue|Black|Red|Yellow) has been eradicated$")
    public void blueHasBeenEradicated(Disease disease) throws Throwable {
        World.eventBus.publish(new EradicationEvent(disease));
    }

    @When("^(.*) treats (.*)$")
    public void scientistTreatsBlue(Role role, Disease disease) throws Throwable {
        Player.as(role).act(new TreatmentOf(disease));
    }

    @Then("^(.*) should not be able to treat (.*)$")
    public void scientistShouldNotBeAbleToTreatBlue(Role role, Disease disease) throws Throwable {
        Assertions.assertThatExceptionOfType(ActionImpossible.class).isThrownBy(() -> Player.as(role).act(new TreatmentOf(disease)));
    }
}

