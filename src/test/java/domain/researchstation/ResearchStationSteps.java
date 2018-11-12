package domain.researchstation;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.actions.revised.researchstation.BuildAResearchStation;
import domain.actions.revised.researchstation.MoveAResearchStation;
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

    @Then("^a research station should (?:have been built|be) on (.*)$")
    public void aResearchStationShouldHaveBeenBuiltOnAtlanta(List<CityName> locations) throws Throwable {
        locations.forEach(l -> {
            try {
                Assertions.assertThat(World.board.researchStations.builtOn(l)).isTrue();
            } catch (ResearchStationException ignored) {
            }
        });
    }

    @When("^(?:Medic|Scientist) build a research station$")
    public void build() throws Throwable {
        World.game.players.currentTurn().player().take(new BuildAResearchStation());
    }

    @Then("^(?:Medic|Scientist) should be able to move a research station$")
    public void medicShouldBeAbleToMoveAResearchStation() throws Throwable {
        Assertions.assertThat(World.game.possibleActions().moveAResearchStation()).isNotEmpty();
    }

    @When("^(?:Medic|Scientist) move a research station from (.*)$")
    public void move(CityName cityName) throws Throwable {
        World.game.players.currentTurn().player().take(new MoveAResearchStation(cityName));
    }

    @And("^(.*) should not have research station anymore$")
    public void madridShouldNotHaveResearchStationAnymore(CityName cityName) throws Throwable {
        Assertions.assertThat(World.board.researchStations.builtOn(cityName)).isFalse();
    }
}
