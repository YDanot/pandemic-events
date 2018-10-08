package domain.infectioncards;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import domain.infection.cards.InfectionCard;
import infra.World;
import org.assertj.core.api.Assertions;

import java.util.Collections;
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

    @And("^Infection draw pile should contains at least (.*)$")
    public void infectionDrawPileShouldContainsAtLeast(List<InfectionCard> cards) throws Throwable {
        Assertions.assertThat(World.game.infectionCardsPiles.drawPile()).containsAll(cards);
    }

    @And("^Infection draw pile starts with (.*)$")
    public void playerDrawPileStartsWithParisBogotaOsakaMoscowMilanManilaAlgiersIstanbulJakarta(List<InfectionCard> cards) throws Throwable {
        Collections.reverse(cards);
        cards.forEach(this::putAtTopOfDeck);
    }


}
