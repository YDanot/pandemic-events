package pandemic.feature;

import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import org.assertj.core.api.Assertions;
import pandemic.OutbreakCounter;
import pandemic.events.World;

public class OutbreakSteps {

    @After
    public void reset(){
        World.reset();
    }
    
    @Then("^outbreak counter value should be (\\d+)$")
    public void outbreakCounterValueShouldBe(int expectedOutbreakCounter) throws Throwable {
        OutbreakCounter outbreakCounter = World.get().outbreakCounter;
        Assertions.assertThat(outbreakCounter.value).isEqualTo(expectedOutbreakCounter);
    }
}
