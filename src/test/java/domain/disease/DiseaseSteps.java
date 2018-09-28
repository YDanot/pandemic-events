package domain.disease;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import domain.infection.Disease;
import infra.World;
import org.assertj.core.api.Assertions;
import run.AsyncAssertions;

import java.util.concurrent.TimeUnit;


public class DiseaseSteps {

    @Then("^There should be (\\d+) (blue|black|red|yellow) cubes available$")
    public void thereShouldBeCubes(int cubeNumber, Disease disease) throws Throwable {
        boolean validated = AsyncAssertions.isTrueWithin(() ->
                        World.game.cubeBank.getRemainingCubes(disease).equals(cubeNumber),
                1, TimeUnit.SECONDS);
        Assertions.assertThat(validated).as("bank " + disease + " cube number").isTrue();
    }


    @And("^(\\d+) (blue|black|red|yellow) cubes has been taken from bank$")
    public void blueCubesHasBeenTakenFromBank(int cubeTaken, Disease disease) throws Throwable {
        for (int i = 0; i < cubeTaken; i++) {
            World.game.cubeBank.takeCube(disease);
        }
    }
}
