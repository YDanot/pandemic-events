package domain.infection.rate;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import infra.World;
import run.AsyncAssertions;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class InfectionRateSteps {

    @And("^the infection rate indicator is placed on (\\d+)(?:nd|st|th|rd) rate$")
    public void theInfectionRateIndicatorIsPlacedOnFirstRate(int rateIndex) throws Throwable {
        for (int i = 1; i < rateIndex; i++) {
            World.game.infectionRateTrack.moveUp();
        }
    }

    @When("^we increase the infection rate$")
    public void weIncreaseTheInfectionRate() throws Throwable {
        World.game.infectionRateTrack.moveUp();
    }

    @Then("^the infection rate should be (\\d+)$")
    public void theInfectionRateShouldBe(int infectionRateValue) throws Throwable {
        AsyncAssertions.within(() -> assertThat(World.game.infectionRateTrack.current()).isEqualTo(infectionRateValue), 10, TimeUnit.SECONDS);
    }
}
