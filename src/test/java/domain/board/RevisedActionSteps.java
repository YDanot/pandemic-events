package domain.board;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.actions.revised.ActionImpossible;
import domain.actions.revised.ShareKnowledge;
import domain.game.Player;
import domain.network.CityName;
import domain.player.cards.PlayerCard;
import domain.role.Role;
import org.assertj.core.api.Assertions;

public class RevisedActionSteps {

    @When("^(.*) shares (.*) with (.*)$")
    public void medicSharesParis(Role role, CityName cityName, Role receiver) throws Throwable {
        new ShareKnowledge(Player.as(role), Player.as(receiver), PlayerCard.valueOf(cityName.name())).execute();
    }

    @Then("^(.*) should not be able to share (.*) with (.*)$")
    public void medicShouldNotBeAbleToShareParisWithScientist(Role role, CityName cityName, Role receiver) throws Throwable {
        Assertions.assertThatExceptionOfType(ActionImpossible.class).isThrownBy(
                () -> new ShareKnowledge(Player.as(role), Player.as(receiver), PlayerCard.valueOf(cityName.name())).execute()
        );
    }
}
