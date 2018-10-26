package domain.researchstation;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.actions.revised.BuildAResearchStation;
import domain.game.Player;
import domain.network.CityName;
import domain.role.Role;
import infra.World;
import org.assertj.core.api.Assertions;

import java.util.List;


public class ResearchStationSteps {

    @Then("^(.*) should be able to build a research station$")
    public void heShouldBeAbleToBuildAResearchStation(Role role) throws Throwable {
        Assertions.assertThat(World.game.possibleActions().buildAResearchStation()).isTrue();
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
        Assertions.assertThat(World.game.possibleActions().buildAResearchStation()).isFalse();
    }

    private void build(Role role) throws ResearchStationException {
        Player.as(role).take(new BuildAResearchStation());
    }

    @Then("^a research station should have been built on (.*)$")
    public void aResearchStationShouldHaveBeenBuiltOnAtlanta(List<CityName> locations) throws Throwable {
        locations.forEach(l -> {
            try {
                Assertions.assertThat(World.board.researchStations.builtOn(l)).isTrue();
            } catch (ResearchStationException ignored) {
            }
        });
    }

    @When("^(?:Medic|Scientist) build a research station$")
    public void medicBuildAResearchStation() throws Throwable {
        build(World.game.players.currentTurn().player().role());
    }
}
