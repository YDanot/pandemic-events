package domain.board;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.game.Player;
import domain.player.cards.PlayerCard;
import domain.player.cards.PlayerHand;
import domain.player.cards.PlayerHands;
import domain.role.Role;
import infra.World;
import org.assertj.core.api.Assertions;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class PlayerCardsSteps {

    @Then("^Player draw pile should contains cities (.*) in any order$")
    public void playerDrawPileShouldContains(List<PlayerCard> cards) throws Throwable {
        Assertions.assertThat(World.board.playerCardsPiles.drawPile()).containsAll(cards);
    }

    @And("^Player draw pile is (.*)$")
    public void playerDrawPileIs(List<PlayerCard> cards) throws Throwable {
        World.board.playerCardsPiles.drawPile().clear();
        Collections.reverse(cards);
        cards.forEach(c -> World.board.playerCardsPiles.drawPile().add(c));
    }

    @Then("^a player should have (.*) in his hand$")
    public void aPlayerShouldHaveInHisHand(List<PlayerCard> playerCards) throws Throwable {
        List<PlayerHand> hands = retrieveHands();
        Assertions.assertThat(hands.stream().anyMatch(h -> h.get().containsAll(playerCards)))
                .as("One player should have " + playerCards + " \n but hands are" + hands).isTrue();
    }

    private List<PlayerHand> retrieveHands() {
        final List<PlayerHand> hands = new ArrayList<>();
        Arrays.stream(Role.values()).forEach(r -> {
            PlayerHand playerHand = World.game.playerHands.handOf(Player.as(r));
            if (playerHand != null) {
                hands.add(playerHand);
            }
        });
        return hands;
    }

    @And("^players are (.*)$")
    public void playersAreMedicScientist(List<Role> roles) throws Throwable {
        PlayerHands.build(roles.stream().map(Player::as).collect(Collectors.toList()));
    }

    @And("^Player draw pile should not contains Epidemic cards$")
    public void playerDrawPileShouldNotContainsEpidemicCards() throws Throwable {
        Assertions.assertThat(World.board.playerCardsPiles.drawPile().contains(PlayerCard.EPIDEMIC)).isFalse();
    }

    @Then("^Player draw pile should contains (\\d+) Epidemic cards$")
    public void playerDrawPileShouldContainsEpidemicCards(int nbEpidemic) throws Throwable {
        Assertions.assertThat(World.board.playerCardsPiles.drawPile().stream()
                .filter(c -> c.equals(PlayerCard.EPIDEMIC)).count()).isEqualTo(nbEpidemic);
    }

    @And("^Player draw pile starts with (.*)$")
    public void playerDrawPileStartsWith(List<PlayerCard> playerCards) throws Throwable {
        Collections.reverse(playerCards);
        playerCards.forEach(this::putAtTopOfDeck);
    }

    private void putAtTopOfDeck(PlayerCard playerCard) {
        Stack<PlayerCard> infectionCardDeck = World.board.playerCardsPiles.drawPile();
        infectionCardDeck.remove(playerCard);
        infectionCardDeck.push(playerCard);
    }

    @And("^Player draw pile should not contains cities (.*)$")
    public void playerDrawPileShouldNotContains(List<PlayerCard> playerCards) throws Throwable {
        Assertions.assertThat(World.board.playerCardsPiles.drawPile()).doesNotContainAnyElementsOf(playerCards);
    }

    @And("^we should have one epidemic card on each 1/(\\d+) of player card pile$")
    public void weShouldHaveOneEpidemicCardOnEachOfPlayerCardPile(int nbParts) throws Throwable {
        Collection<List<PlayerCard>> partitioned = partitioned(World.board.playerCardsPiles.drawPile(), nbParts);
        Assertions.assertThat(partitioned).allMatch(p -> p.contains(PlayerCard.EPIDEMIC));
    }

    private Collection<List<PlayerCard>> partitioned(List<PlayerCard> draw, int parts) {
        int partSize = BigDecimal.valueOf(draw.size()).divide(BigDecimal.valueOf(parts), BigDecimal.ROUND_HALF_UP).intValue();
        final AtomicInteger counter = new AtomicInteger(0);

        return draw.stream()
                .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / partSize))
                .values();
    }

    @And("^(.*) hand is (.*)$")
    public void handIs(Role role, List<PlayerCard> playerCards) throws Throwable {
        PlayerHand playerHand = World.game.playerHands.handOf(Player.as(role));
        playerCards.forEach(
                p -> {
                    this.putAtTopOfDeck(p);
                    playerHand.deal(World.board.playerCardsPiles.draw());
                });
    }

    @And("^(.*) hand should be (.*)$")
    public void handShouldBe(Role role, List<PlayerCard> playerCards) throws Throwable {
        playerCards.forEach(playerCard ->
                Assertions.assertThat(World.game.playerHands.handOf(Player.as(role)).contains(playerCard)).isTrue());
    }

    @And("^the player discard pile should contains (.*)")
    public void thePlayerDiscardPileShouldContains(List<PlayerCard> playerCards) throws Throwable {
        Assertions.assertThat(World.board.playerCardsPiles.discardPile()).containsAll(playerCards);
    }

    @When("^a player draws a card$")
    public void aPlayerDrawsACard() throws Throwable {
        World.board.playerCardsPiles.draw();
    }
}
