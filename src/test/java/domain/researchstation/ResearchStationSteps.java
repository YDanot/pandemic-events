package domain.researchstation;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import domain.network.CityName;
import domain.role.Role;
import infra.World;
import org.assertj.core.api.Assertions;

import java.util.List;


public class ResearchStationSteps {

    @Then("^(.*) should be able to build a research station$")
    public void heShouldBeAbleToBuildAResearchStation(Role role) throws Throwable {
        CityName location = build(role);
        Assertions.assertThat(World.game.researchStations.builtOn(location)).isTrue();
    }

    @And("^a research station has been built on (.*)")
    public void aResearchStationHasBeenBuiltOnAlger(List<CityName> locations) throws Throwable {
        locations.forEach(l -> {
            try {
                World.game.researchStations.buildOn(l);
            } catch (ResearchStationException ignored) {
            }
        });
    }

    @Then("^(.*) should not be able to build a research station$")
    public void medicShouldNotBeAbleToBuildAResearchStation(Role role) throws Throwable {
        Assertions.assertThatExceptionOfType(Exception.class).isThrownBy(() -> build(role));
    }

    private CityName build(Role role) throws ResearchStationException {
        CityName location = World.game.locations.locationsOf(role);
        World.game.researchStations.buildOn(location);
        return location;
    }
}
