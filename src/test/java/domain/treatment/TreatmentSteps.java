package domain.treatment;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import domain.infection.Disease;
import domain.network.CityName;
import domain.treatment.cure.EradicationEvent;
import infra.World;


public class TreatmentSteps {

    @When("^(.*) is treated in (.*)$")
    public void parisIsTreated(Disease disease, CityName cityName) throws Throwable {
        World.eventBus.publish(new TreatmentEvent(disease, cityName));
    }

    @And("^(Blue|Black|Red|Yellow) has been eradicated$")
    public void blueHasBeenEradicated(Disease disease) throws Throwable {
        World.eventBus.publish(new EradicationEvent(disease));
    }
}

