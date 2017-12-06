package pandemic.feature;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.assertj.core.api.Assertions;
import pandemic.City;
import pandemic.InfectionLevel;
import pandemic.Network;
import pandemic.events.World;

import java.util.List;
import java.util.stream.Stream;

import static pandemic.City.*;
import static pandemic.InfectionLevel.from;

public class CitySteps {

    @After
    public void reset(){
        World.reset();
    }

    @Given("^the occident initial sub-network$")
    public void the_occident_sub_network() throws Throwable {
        getNetwork().addLink(PARIS, LONDON);
        getNetwork().addLink(PARIS, MADRID);
        getNetwork().addLink(PARIS, ESSEN);
        getNetwork().addLink(PARIS, MILAN);
        getNetwork().addLink(PARIS, ALGER);
        getNetwork().addLink(LONDON, ESSEN);
    }

    @Then("^the cities should have the following infection levels:$")
    public void the_cities_should_have_the_following_infection_levels(List<CityInfectionLevel> expectedCityInfectionLevels) throws Throwable {
        expectedCityInfectionLevels.forEach(cityInfectionLevel ->
                Assertions.assertThat(cityInfectionLevel.city.level).isEqualTo(cityInfectionLevel.getLevel())
        );
    }

    @And("^(.*) should be linked to ([^\"]*).$")
    public void cityShouldBeLinkedToLinkedCities(City city, List<City> linkedCities) throws Throwable {
        Assertions.assertThat(citiesLinkedTo(city).allMatch(linkedCities::contains)).isTrue();
    }

    private static class CityInfectionLevel {

        final City city;
        final int level;

        public CityInfectionLevel(City city, int level) {
            this.city = city;
            this.level = level;
        }

        public InfectionLevel getLevel() {
            return from(level);
        }
    }

    private Stream<City> citiesLinkedTo(City city) {
        return getNetwork().citiesLinkedTo(city);
    }

    private Network getNetwork() {
        return World.get().network;
    }
}
