package domain.action.basics;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import domain.game.ForbiddenMoveException;
import domain.network.CityName;
import domain.role.Role;
import infra.World;
import org.assertj.core.api.Assertions;

import java.util.List;


public class BasicActionsSteps {

    @And("^(.*) is located at (.*)")
    public void isLocatedAt(Role role, CityName cityName) throws Throwable {
        World.game.locations.move(role, cityName);
    }

    @Then("^(.*) should be able to drive to (.*)$")
    public void scientistShouldBeAbleToMoveTo(Role role, List<CityName> cityNames) throws Throwable {
        moveTo(role, cityNames);
    }

    @Then("^(.*) should not be able to drive to (.*)")
    public void scientistShouldNotBeAbleToMoveTo(Role role, List<CityName> cityNames) throws Throwable {
        Assertions.assertThatExceptionOfType(ForbiddenMoveException.class).isThrownBy(() -> moveTo(role, cityNames));
    }

    private void moveTo(Role role, List<CityName> cityNames) throws ForbiddenMoveException {
        CityName from = World.game.locations.locationsOf(role);

        for (CityName cityName : cityNames) {
            World.game.locations.drive(role, cityName);
            World.game.locations.drive(role, from);
        }
    }

}
