package domain.board;

import cucumber.api.java.en.Then;
import domain.game.GameState;
import infra.World;
import org.assertj.core.api.Assertions;


public class GameSteps {


    @Then("^game should be (.*)")
    public void gameShouldBeAvailable(GameState gameState) throws Throwable {
        Assertions.assertThat(World.gameState).isEqualTo(gameState);
    }
}
