package domain.board;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.actions.role.researcher.ShareWithMe;
import domain.game.Player;
import domain.network.CityName;
import domain.player.cards.PlayerCard;
import domain.role.Role;
import infra.World;
import org.assertj.core.api.Assertions;

public class ResearcherActionSteps {

    @When("^(Medic|Scientist) ask Researcher to share (.*)$")
    public void scientistAskResearcherToShareBaghdad(Role doer, CityName cityName) throws Throwable {
        Player.as(doer).take(new ShareWithMe(PlayerCard.valueOf(cityName.name())));
    }

    @Then("^(?:Medic|Scientist) should be able to ask Researcher to share knowledge$")
    public void scientistShouldBeAbleToAskResearcherToShareKnowledge() throws Throwable {
        Assertions.assertThat(World.game.possibleActions().shareWithMe()).isTrue();
    }
}
