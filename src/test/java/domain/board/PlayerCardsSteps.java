package domain.board;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.player.cards.PlayerCard;
import domain.player.cards.PlayerHand;
import domain.role.Role;
import infra.World;
import org.assertj.core.api.Assertions;

import java.util.*;

public class PlayerCardsSteps {

    @Then("^Player draw pile should contains at least (.*)$")
    public void playerDrawPileShouldContainsAtLeast(List<PlayerCard> cards) throws Throwable {
        Assertions.assertThat(World.game.playerCardsPiles.drawPile()).containsAll(cards);
    }

    @And("^Player draw pile is (.*)$")
    public void playerDrawPileIsParisLondonNew_YorkEssenMilanMadridAlgiersEpidemic(List<PlayerCard> cards) throws Throwable {
        World.game.playerCardsPiles.drawPile().clear();
        Collections.reverse(cards);
        cards.forEach(c -> World.game.playerCardsPiles.drawPile().add(c));
    }

    @When("^the game starts$")
    public void theGameStarts() throws Throwable {
        World.game.start();
    }

    @Then("^a player should have (.*) in his hand$")
    public void aPlayerShouldHaveInHisHand(List<PlayerCard> playerCards) throws Throwable {
        Map<Role, PlayerHand> hands = retrieveHands();

        Assertions.assertThat(hands.values()
                .stream().anyMatch(h -> h.get().containsAll(playerCards))).isTrue();

    }

    private Map<Role, PlayerHand> retrieveHands() {
        final Map<Role, PlayerHand> hands = new HashMap<>();
        Arrays.stream(Role.values()).forEach(r -> {
            PlayerHand playerHand = World.game.playerHands.handOf(r);
            if (playerHand != null) {
                hands.put(r, playerHand);
            }
        });
        return hands;
    }

    @And("^players are (.*)$")
    public void playersAreMedicScientist(List<Role> roles) throws Throwable {
        World.game.playerHands.build(roles);
    }
}
