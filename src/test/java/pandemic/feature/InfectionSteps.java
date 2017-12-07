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

    @When("^(.*) is infected by (Blue|Black|Red|Yellow)$")
    public void cityIsInfected(CityName cityName, Disease disease) throws Throwable {
        World.eventBus.publish(new InfectionEvent(disease, World.network.get(cityName), new TurnId()));
    }

    @Then("^(Blue|Black|Red|Yellow) infection level of (.*) should (?:be|stay at) (\\d+)$")
    public void infectionLevelOfParisShouldBe(Disease disease, CityName cityName, int infectionLevel) throws Throwable {
        Assertions.assertThat(World.network.get(cityName).infectionLevelFor(disease)).isEqualTo(InfectionLevel.from(infectionLevel));
    }

    @And("^(.*) has already been infected by (Blue|Black|Red|Yellow) (\\d+) times$")
    public void cityHasAlreadyBeenInfectedTimes(CityName cityName, Disease disease, int infectionTimes) throws Throwable {
        for (int i = 0; i < infectionTimes; i++) {
            World.network.get(cityName).infect(disease);
        }
    }

}
