package domain.infection.outbreak;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import domain.game.TurnId;
import domain.infection.Disease;
import domain.network.CityName;
import infra.World;
import org.assertj.core.api.Assertions;
import run.AsyncAssertions;

import java.util.concurrent.TimeUnit;

public class OutbreakSteps {

    @Then("^outbreak counter value should be (\\d+)$")
    public void outbreakCounterValueShouldBe(int expectedOutbreakCounter) throws Throwable {
        Assertions.assertThat(AsyncAssertions.isTrueWithin(() -> World.game.outbreakCounter.value == expectedOutbreakCounter, 1, TimeUnit.SECONDS))
                .as("outbreak counter should be " + expectedOutbreakCounter + " but was " + World.game.outbreakCounter.value).isTrue();
    }

    @And("^there already were (\\d+) outbreaks$")
    public void thereAlreadyWereOutbreaks(int times) throws Throwable {
        for (int i = 0; i < times; i++) {
            World.game.outbreakCounter.onOutbreak(new OutbreakEvent(CityName.PARIS, Disease.BLUE, new TurnId()));
        }
    }
}
