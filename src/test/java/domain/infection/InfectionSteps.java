package domain.infection;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.game.TurnId;
import domain.network.City;
import domain.network.CityName;
import infra.AsyncEventBus;
import infra.World;

import static org.assertj.core.api.Assertions.assertThat;

public class InfectionSteps {

    private TurnId currentTurnId;

    @Before
    public void startTurn() {
        currentTurnId = new TurnId();
    }

    @When("^(.*) is infected by (Blue|Black|Red|Yellow)$")
    public void cityIsInfected(CityName cityName, Disease disease) throws Throwable {
        City city = World.board.network.get(cityName);
        World.eventBus.publish(new InfectionEvent(disease, cityName, currentTurnId, city.infectionLevelFor(disease)));
    }

    @Then("^(Blue|Black|Red|Yellow) infection level of (.*) should (?:be|stay at) (\\d+)$")
    public void infectionLevelOfParisShouldBe(Disease disease, CityName cityName, int infectionLevel) throws Throwable {
        assertThat(World.board.network.get(cityName).infectionLevelFor(disease)).isEqualTo(InfectionLevel.from(infectionLevel));

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
        City city = World.board.network.get(cityName);

        while (!city.isHealthyFor(Disease.BLUE)) {
            city.treat(disease);
            World.board.cubeBank.putBackCube(Disease.BLUE);
        }
        for (int i = 0; i < infectionTimes; i++) {
            World.eventBus.publish(new InfectionEvent(disease, cityName, currentTurnId, city.infectionLevelFor(disease)));
            if (World.eventBus instanceof AsyncEventBus) {
                Thread.sleep(10);
            }
        }
    }
}
