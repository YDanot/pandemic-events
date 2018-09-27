package domain.treatment;

import cucumber.api.java.en.When;
import domain.board.CityName;
import domain.infection.Disease;
import infra.World;


public class TreatmentSteps {

    @When("^(.*) is treated in (.*)$")
    public void parisIsTreated(Disease disease, CityName cityName) throws Throwable {
        World.eventBus.publish(new TreatmentEvent(disease, cityName));
    }


}

