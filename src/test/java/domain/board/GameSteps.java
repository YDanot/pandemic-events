package domain.board;

import cucumber.api.java.en.Then;
import domain.game.GameState;
import infra.World;
import org.assertj.core.api.Assertions;


public class GameSteps {

    @Then("^game should be (available|lost|won)")
    public void gameShouldBeAvailable(GameState gameState) throws Throwable {
        Assertions.assertThat(World.game.gameState).isEqualTo(gameState);
    }
}
