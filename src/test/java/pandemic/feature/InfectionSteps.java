package pandemic.feature;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.Assertions;
import pandemic.City;
import pandemic.InfectionLevel;
import pandemic.events.InfectionEvent;
import pandemic.events.World;


public class InfectionSteps {

    @After
    public void reset(){
        World.reset();
    }

    @When("^(.*) is infected$")
    public void cityIsInfected(City city) throws Throwable {
        World.get().eventBus.publish(new InfectionEvent(city));
    }

    @Then("^infection level of (.*) should (?:be|stay at) (\\d+)$")
    public void infectionLevelOfParisShouldBe(City city, int infectionLevel) throws Throwable {
        Assertions.assertThat(city.level).isEqualTo(InfectionLevel.from(infectionLevel));
    }

    @And("^(.*) has already been infected (\\d+) times$")
    public void cityHasAlreadyBeenInfectedTimes(City city, int infectionTimes) throws Throwable {
        for (int i = 0; i < infectionTimes; i++) {
            city.infect();
        }
    }
}
