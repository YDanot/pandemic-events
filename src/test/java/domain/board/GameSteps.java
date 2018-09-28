package domain.board;

import cucumber.api.java.en.Then;
import domain.game.GameState;
import infra.World;
import org.assertj.core.api.Assertions;
import run.AsyncAssertions;

import java.util.concurrent.TimeUnit;


public class GameSteps {

    @Then("^game should be (available|lost|won)")
    public void gameShouldBeAvailable(GameState gameState) throws Throwable {
        boolean validated = AsyncAssertions.isTrueWithin(() ->
                        World.game.gameState == gameState,
                1, TimeUnit.SECONDS);
        Assertions.assertThat(validated).as("game state should be " + gameState).isTrue();
    }
}
