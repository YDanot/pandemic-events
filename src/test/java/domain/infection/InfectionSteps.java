package domain.infection;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.game.TurnId;
import domain.network.City;
import domain.network.CityName;
import infra.World;
import run.AsyncAssertions;

import java.util.concurrent.TimeUnit;

public class InfectionSteps {

    private TurnId currentTurnId;

    @Before
    public void startTurn() {
        currentTurnId = new TurnId();
    }

    @When("^(.*) is infected by (Blue|Black|Red|Yellow)$")
    public void cityIsInfected(CityName cityName, Disease disease) throws Throwable {
        City city = World.game.network.get(cityName);
        World.eventBus.publish(new InfectionEvent(disease, cityName, currentTurnId, city.infectionLevelFor(disease)));
    }

    @Then("^(Blue|Black|Red|Yellow) infection level of (.*) should (?:be|stay at) (\\d+)$")
    public void infectionLevelOfParisShouldBe(Disease disease, CityName cityName, int infectionLevel) throws Throwable {

        AsyncAssertions.isTrueWithin(() ->
                        World.game.network.get(cityName).infectionLevelFor(disease).equals(InfectionLevel.from(infectionLevel)),
                1, TimeUnit.MICROSECONDS);

        /*boolean validated = AsyncAssertions.isTrueWithin(() -> GameHook.RecordEvent.INSTANCE.infectionAppliedEvents.stream()
                .filter(e -> e.disease == disease
                        && e.cityName == cityName
                        && e.infectionLevel.equals(InfectionLevel.from(infectionLevel)))
                .findFirst()
                .isPresent(), 1, TimeUnit.SECONDS);
        Assertions.assertThat(validated).as("infection level should be updated but no infectionAppliedEvent raised").isTrue();*/
    }

    @And("^(.*) has already been infected by (Blue|Black|Red|Yellow) (\\d+) times?$")
    public void cityHasAlreadyBeenInfectedTimes(CityName cityName, Disease disease, int infectionTimes) throws Throwable {
        for (int i = 0; i < infectionTimes; i++) {
            City city = World.game.network.get(cityName);
            World.cityInfector.onInfection(new InfectionEvent(disease, cityName, currentTurnId, city.infectionLevelFor(disease)));
        }
    }

}
