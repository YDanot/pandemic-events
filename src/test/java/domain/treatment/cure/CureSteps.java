package domain.treatment.cure;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.actions.ActionImpossible;
import domain.actions.revised.curing.CureDisease;
import domain.game.Player;
import domain.infection.Disease;
import domain.player.cards.PlayerCard;
import domain.player.cards.PlayerHand;
import domain.role.Role;
import infra.World;
import org.assertj.core.api.Assertions;
import run.AsyncAssertions;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


public class CureSteps {

    @When("^(Blue|Black|Red|Yellow) is cured$")
    public void blueIsCured(Disease disease) throws Throwable {
        World.eventBus.publish(new CureDiscoveringEvent(disease));
    }

    @Then("^(Blue|Black|Red|Yellow) should be mark as cured$")
    public void blueShouldBeMarkAsCured(Disease disease) throws Throwable {
        AsyncAssertions.isTrueWithin(() ->
                        World.board.cureMarkerArea.hasBeenCured(disease),
                1, TimeUnit.SECONDS);
    }

    @And("^(Blue|Black|Red|Yellow) has been cured$")
    public void blueHasBeenCured(Disease disease) throws Throwable {
        World.eventBus.publish(new CureDiscoveringEvent(disease));
    }

    @When("^(.*) cures (Blue|Black|Red|Yellow) disease by discarding (.*)$")
    public void medicCuresBlueDisease(Role role, Disease disease, List<PlayerCard> subHand) throws Throwable {
        Player.as(role).take(new CureDisease(disease, World.game.playerHands.handOf(Player.as(role)).subHand(subHand)));
    }

    @Then("^(.*) should not be able to cure (Blue|Black|Red|Yellow) disease$")
    public void medicShouldNotBeAbleToCureBlueDisease(Role role, Disease disease) throws Throwable {
        Assertions.assertThat(cure(role, disease)).isPresent();
    }

    private Optional<ActionImpossible> cure(Role role, Disease disease) {
        PlayerHand playerHand = World.game.playerHands.handOf(Player.as(role));
        return Player.as(role).take(new CureDisease(disease, playerHand.subHand(playerHand.get())));
    }

    @Then("^(?:.*) should be able to cure (Blue|Black|Red|Yellow) Disease$")
    public void medicShouldBeAbleToCureBlueDisease(Disease disease) throws Throwable {
        Assertions.assertThat(World.game.possibleActions().cure()).contains(disease);
    }

    @Then("^(Blue|Black|Red|Yellow) should have been cured$")
    public void blueShouldHaveBeenCured(Disease disease) throws Throwable {
        Assertions.assertThat(World.board.cureMarkerArea.hasBeenCured(disease));
    }
}
