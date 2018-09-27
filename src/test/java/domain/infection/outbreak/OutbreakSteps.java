package domain.infection.outbreak;

import cucumber.api.java.en.And;
import domain.network.CityName;
import domain.game.TurnId;
import domain.infection.Disease;
import org.assertj.core.api.Assertions;

import cucumber.api.java.en.Then;
import infra.World;

public class OutbreakSteps {

    @Then("^outbreak counter value should be (\\d+)$")
    public void outbreakCounterValueShouldBe(int expectedOutbreakCounter) throws Throwable {
        OutbreakCounter outbreakCounter = World.game.outbreakCounter;
        Assertions.assertThat(outbreakCounter.value).as("outbreak counter").isEqualTo(expectedOutbreakCounter);
    }

    @And("^there already were (\\d+) outbreaks$")
    public void thereAlreadyWereOutbreaks(int times) throws Throwable {
        for (int i = 0; i < times; i++) {
            World.eventBus.publish(new OutbreakEvent(CityName.PARIS, Disease.BLUE, new TurnId()));
        }
    }
}
