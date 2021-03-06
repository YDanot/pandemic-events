package domain.board;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.actions.revised.sharing.ShareKnowledge;
import domain.game.Player;
import domain.network.CityName;
import domain.player.cards.PlayerCard;
import domain.role.Role;
import infra.World;
import org.assertj.core.api.Assertions;

public class RevisedActionSteps {

    @When("^(.*) shares (.*) with (.*)$")
    public void medicSharesParis(Role doer, CityName cityName, Role receiver) throws Throwable {
        Player.as(doer).take(new ShareKnowledge(Player.as(receiver), PlayerCard.valueOf(cityName.name())));
    }

    @Then("^(.*) should not be able to share (.*) with (.*)$")
    public void medicShouldNotBeAbleToShareParisWithScientist(Role doer, CityName cityName, Role receiver) throws Throwable {
        Assertions.assertThat(Player.as(doer).take(new ShareKnowledge(Player.as(receiver), PlayerCard.valueOf(cityName.name())))).isPresent();
    }

    @Then("^(?:.*) should be able to share knowledge$")
    public void medicShouldBeAbleToShareParis() throws Throwable {
        Assertions.assertThat(World.game.possibleActions().share()).isTrue();
    }
}
