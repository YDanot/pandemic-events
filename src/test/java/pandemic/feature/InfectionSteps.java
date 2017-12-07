package pandemic.feature;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.infection.Disease;
import domain.game.TurnId;
import org.assertj.core.api.Assertions;
import domain.board.CityName;
import domain.infection.InfectionLevel;
import domain.events.InfectionEvent;
import infra.World;

public class InfectionSteps {

    @When("^(.*) is infected$")
    public void cityIsInfected(CityName cityName) throws Throwable {
        World.eventBus.publish(new InfectionEvent(World.network.get(cityName), new TurnId()));
    }

    @Then("^infection level of (.*) should (?:be|stay at) (\\d+)$")
    public void infectionLevelOfParisShouldBe(CityName cityName, int infectionLevel) throws Throwable {
        Assertions.assertThat(World.network.get(cityName).infectionLevel()).isEqualTo(InfectionLevel.from(infectionLevel));
    }

    @And("^(.*) has already been infected (\\d+) times$")
    public void cityHasAlreadyBeenInfectedTimes(CityName cityName, int infectionTimes) throws Throwable {
        for (int i = 0; i < infectionTimes; i++) {
            World.network.get(cityName).infect(Disease.BLUE);
        }
    }
}
