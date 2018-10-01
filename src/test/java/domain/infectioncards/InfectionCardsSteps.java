package domain.infectioncards;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import domain.infection.cards.InfectionCard;
import infra.World;

import java.util.List;
import java.util.Stack;

public class InfectionCardsSteps {

    @When("^(.*) infection cards is drawn$")
    public void infectionCardsIsDrawn(InfectionCard infectionCard) throws Throwable {
        putAtTopOfDeck(infectionCard);
        World.game.infectionCardsPiles.draw();
    }

    void putAtTopOfDeck(InfectionCard infectionCard) {
        Stack<InfectionCard> infectionCardDeck = World.game.infectionCardsPiles.drawPile();
        infectionCardDeck.remove(infectionCard);
        infectionCardDeck.push(infectionCard);
    }

    private void putAtBottomOfDeck(InfectionCard infectionCard) {
        Stack<InfectionCard> infectionCardDeck = World.game.infectionCardsPiles.drawPile();
        infectionCardDeck.remove(infectionCard);
        infectionCardDeck.insertElementAt(infectionCard, infectionCardDeck.size());
    }

    @And("^the bottom card from the Infection Draw Pile is (.*)$")
    public void theBottomCardFromTheInfectionDrawPileIsParis(InfectionCard infectionCard) throws Throwable {
        putAtBottomOfDeck(infectionCard);
    }

    @And("^the infection discard pile is (.*)$")
    public void theInfectionDiscardPileIsParisAlgiersNew_York(List<InfectionCard> infectionCardList) throws Throwable {
        for (InfectionCard infectionCard : infectionCardList) {
            putAtTopOfDeck(infectionCard);
            World.game.infectionCardsPiles.draw();
        }
    }
}
