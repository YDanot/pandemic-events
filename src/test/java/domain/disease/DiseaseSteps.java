package domain.disease;

import cucumber.api.java.en.Then;
import domain.infection.Disease;
import infra.World;
import org.assertj.core.api.Assertions;


public class DiseaseSteps {

    @Then("^There should be (\\d+) (blue|black) cubes available$")
    public void thereShouldBeCubes(int cubeNumber, Disease disease) throws Throwable {
        Assertions.assertThat(World.cubeBank.getRemainingCubes(disease)).isEqualTo(cubeNumber);
    }
}
