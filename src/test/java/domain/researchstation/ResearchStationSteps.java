package domain.researchstation;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import domain.network.CityName;
import domain.role.Role;
import infra.World;
import org.assertj.core.api.Assertions;


public class ResearchStationSteps {

    @Then("^(.*) should be able to build a research station$")
    public void heShouldBeAbleToBuildAResearchStation(Role role) throws Throwable {
        CityName location = build(role);
        Assertions.assertThat(World.game.researchStations.builtOn(location)).isTrue();
    }

    @And("^a research station has been built on (.*)")
    public void aResearchStationHasBeenBuiltOnAlger(CityName location) throws Throwable {
        World.game.researchStations.buildOn(location);
    }

    @Then("^(.*) should not be able to build a research station$")
    public void medicShouldNotBeAbleToBuildAResearchStation(Role role) throws Throwable {
        Assertions.assertThatExceptionOfType(OnlyOneResearchStationException.class).isThrownBy(() -> build(role));
    }

    private CityName build(Role role) throws OnlyOneResearchStationException {
        CityName location = World.game.locations.locationsOf(role);
        World.game.researchStations.buildOn(location);
        return location;
    }
}
