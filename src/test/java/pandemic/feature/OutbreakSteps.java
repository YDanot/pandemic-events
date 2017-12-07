package pandemic.feature;

import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import org.assertj.core.api.Assertions;
import domain.OutbreakCounter;
import infra.World;

public class OutbreakSteps {

    @After
    public void reset(){
        World.reset();
    }
    
    @Then("^outbreak counter value should be (\\d+)$")
    public void outbreakCounterValueShouldBe(int expectedOutbreakCounter) throws Throwable {
        OutbreakCounter outbreakCounter = World.getOutbreakCounter();
        Assertions.assertThat(outbreakCounter.value).isEqualTo(expectedOutbreakCounter);
    }
}
