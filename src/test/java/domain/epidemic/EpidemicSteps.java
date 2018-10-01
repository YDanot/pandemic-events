package domain.epidemic;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.infection.cards.InfectionCard;
import infra.World;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.Stack;

public class EpidemicSteps {
    @When("^an epidemic occurred$")
    public void anEpidemicOccurred() throws Throwable {
        World.eventBus.publish(new EpidemicEvent());
    }

    @Then("^top cards of the Infection Draw Pile should be (.*)$")
    public void topCardsOfTheInfectionDrawPileShouldBeParisAlgiersNew_York(List<InfectionCard> infectionCardList) throws Throwable {
        Stack<InfectionCard> drawPile = World.game.infectionCardsPiles.drawPile();
        for (int i = 0; i < infectionCardList.size(); i++) {
            Assertions.assertThat(drawPile.pop()).isIn(infectionCardList);
        }
    }
}
