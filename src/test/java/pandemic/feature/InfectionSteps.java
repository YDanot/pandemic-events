package pandemic.feature;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.Disease;
import org.assertj.core.api.Assertions;
import domain.CityName;
import domain.InfectionLevel;
import domain.events.InfectionEvent;
import infra.World;

import static infra.World.getNetwork;

public class InfectionSteps {

    @After
    public void reset(){
        World.reset();
    }

    @When("^(.*) is infected$")
    public void cityIsInfected(CityName cityName) throws Throwable {
        World.publish(new InfectionEvent(getNetwork().get(cityName)));
    }

    @Then("^infection level of (.*) should (?:be|stay at) (\\d+)$")
    public void infectionLevelOfParisShouldBe(CityName cityName, int infectionLevel) throws Throwable {
        Assertions.assertThat(World.getNetwork().get(cityName).level()).isEqualTo(InfectionLevel.from(infectionLevel));
    }

    @And("^(.*) has already been infected (\\d+) times$")
    public void cityHasAlreadyBeenInfectedTimes(CityName cityName, int infectionTimes) throws Throwable {
        for (int i = 0; i < infectionTimes; i++) {
            World.getNetwork().get(cityName).infect(Disease.BLUE);
        }
    }
}
