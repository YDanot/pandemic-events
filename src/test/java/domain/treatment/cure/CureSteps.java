package domain.treatment.cure;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.infection.Disease;
import infra.World;
import run.AsyncAssertions;

import java.util.concurrent.TimeUnit;


public class CureSteps {

    @When("^(Blue|Black|Red|Yellow) is cured$")
    public void blueIsCured(Disease disease) throws Throwable {
        World.eventBus.publish(new CureDiscoveringEvent(disease));
    }

    @Then("^(Blue|Black|Red|Yellow) should be mark as cured$")
    public void blueShouldBeMarkAsCured(Disease disease) throws Throwable {
        AsyncAssertions.isTrueWithin(() ->
                        World.game.cureMarkerArea.hasBeenCured(disease),
                1, TimeUnit.SECONDS);
    }

    @And("^(Blue|Black|Red|Yellow) has been cured$")
    public void blueHasBeenCured(Disease disease) throws Throwable {
        World.eventBus.publish(new CureDiscoveringEvent(disease));
    }
}
