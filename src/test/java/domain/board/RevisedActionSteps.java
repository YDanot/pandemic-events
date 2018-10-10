package domain.board;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.actions.ActionImpossible;
import domain.actions.revised.ShareKnowledge;
import domain.game.Player;
import domain.network.CityName;
import domain.player.cards.PlayerCard;
import domain.role.Role;
import org.assertj.core.api.Assertions;

public class RevisedActionSteps {

    @When("^(.*) shares (.*) with (.*)$")
    public void medicSharesParis(Role doer, CityName cityName, Role receiver) throws Throwable {
        Player.as(doer).act(new ShareKnowledge(Player.as(receiver), PlayerCard.valueOf(cityName.name())));
    }

    @Then("^(.*) should not be able to share (.*) with (.*)$")
    public void medicShouldNotBeAbleToShareParisWithScientist(Role doer, CityName cityName, Role receiver) throws Throwable {
        Assertions.assertThatExceptionOfType(ActionImpossible.class).isThrownBy(
                () -> Player.as(doer).act(new ShareKnowledge(Player.as(receiver), PlayerCard.valueOf(cityName.name())))
        );
    }
}
