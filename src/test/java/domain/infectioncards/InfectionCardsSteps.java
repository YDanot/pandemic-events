package domain.infectioncards;

import cucumber.api.java.en.When;
import domain.infection.cards.InfectionCard;
import infra.World;

import java.util.Stack;

public class InfectionCardsSteps {

    @When("^(.*) infection cards is drawn$")
    public void infectionCardsIsDrawn(InfectionCard infectionCard) throws Throwable {
        putAtTopOfDeck(infectionCard);
        World.game.infectionCardsPiles.draw();
    }

    public void putAtTopOfDeck(InfectionCard infectionCard) {
        Stack<InfectionCard> infectionCardDeck = World.game.infectionCardsPiles.drawPile();
        infectionCardDeck.remove(infectionCard);
        infectionCardDeck.push(infectionCard);
    }

}
