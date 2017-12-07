package pandemic.feature;

import org.assertj.core.api.Assertions;

import cucumber.api.java.en.Then;
import domain.infection.outbreak.OutbreakCounter;
import infra.World;

public class OutbreakSteps {

    @Then("^outbreak counter value should be (\\d+)$")
    public void outbreakCounterValueShouldBe(int expectedOutbreakCounter) throws Throwable {
        OutbreakCounter outbreakCounter = World.outbreakCounter;
        Assertions.assertThat(outbreakCounter.value).as("outbreak counter").isEqualTo(expectedOutbreakCounter);
    }
}
