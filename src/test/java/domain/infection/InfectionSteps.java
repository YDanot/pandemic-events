package domain.infection;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.network.City;
import domain.network.CityName;
import domain.game.TurnId;
import infra.World;
import org.assertj.core.api.Assertions;

public class InfectionSteps {

    private TurnId currentTurnId;

    @Before
    public void startTurn() {
        currentTurnId = new TurnId();
    }

    @When("^(.*) is infected by (Blue|Black|Red|Yellow)$")
    public void cityIsInfected(CityName cityName, Disease disease) throws Throwable {
        infect(cityName, disease);
    }

    @Then("^(Blue|Black|Red|Yellow) infection level of (.*) should (?:be|stay at) (\\d+)$")
    public void infectionLevelOfParisShouldBe(Disease disease, CityName cityName, int infectionLevel) throws Throwable {
        Assertions.assertThat(World.game.network.get(cityName).infectionLevelFor(disease)).isEqualTo(InfectionLevel.from(infectionLevel));
    }

    @And("^(.*) has already been infected by (Blue|Black|Red|Yellow) (\\d+) times?$")
    public void cityHasAlreadyBeenInfectedTimes(CityName cityName, Disease disease, int infectionTimes) throws Throwable {
        for (int i = 0; i < infectionTimes; i++) {
            infect(cityName, disease);
        }
    }

    private void infect(CityName cityName, Disease disease) {
        City city = World.game.network.get(cityName);
        World.eventBus.publish(new InfectionEvent(disease, cityName, currentTurnId, city.infectionLevelFor(disease)));
    }

}
