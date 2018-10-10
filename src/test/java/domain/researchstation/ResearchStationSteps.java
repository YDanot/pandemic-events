package domain.researchstation;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import domain.actions.revised.BuildAResearchStation;
import domain.game.Player;
import domain.network.CityName;
import domain.role.Role;
import infra.World;
import org.assertj.core.api.Assertions;
import run.AsyncAssertions;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class ResearchStationSteps {

    @Then("^(.*) should be able to build a research station$")
    public void heShouldBeAbleToBuildAResearchStation(Role role) throws Throwable {
        build(role);
        AsyncAssertions.isTrueWithin(() ->
                        World.board.researchStations.builtOn(World.board.locations.locationsOf(role)),
                1, TimeUnit.SECONDS);
    }

    @And("^a research station has been built on (.*)")
    public void aResearchStationHasBeenBuiltOnAlgiers(List<CityName> locations) throws Throwable {
        locations.forEach(l -> {
            try {
                World.board.researchStations.buildOn(l);
            } catch (ResearchStationException ignored) {
            }
        });
    }

    @Then("^(.*) should not be able to build a research station$")
    public void medicShouldNotBeAbleToBuildAResearchStation(Role role) throws Throwable {
        Assertions.assertThatExceptionOfType(Exception.class).isThrownBy(() -> build(role));
    }

    private void build(Role role) throws ResearchStationException {
        Player.as(role).act(new BuildAResearchStation());
    }
}
