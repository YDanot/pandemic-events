package domain.board;

import cucumber.api.java.en.Then;
import domain.player.cards.PlayerCard;
import infra.World;
import org.assertj.core.api.Assertions;

import java.util.List;

public class PlayerCardsSteps {

    @Then("^Player draw pile should contains at least (.*)$")
    public void playerDrawPileShouldContainsAtLeast(List<PlayerCard> cards) throws Throwable {
        Assertions.assertThat(World.game.playerCardsPiles.drawPile()).containsAll(cards);
    }
}
