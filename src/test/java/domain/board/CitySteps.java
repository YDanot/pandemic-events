
package domain.board;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import domain.infection.Disease;
import domain.infection.InfectionLevel;
import domain.infection.cards.InfectionCard;
import domain.network.CityName;
import domain.network.Network;
import infra.World;
import org.assertj.core.api.Assertions;
import run.AsyncAssertions;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static domain.infection.InfectionLevel.from;
import static domain.network.CityName.*;

public class CitySteps {

    @Given("^the occident initial sub-network$")
    public void the_occident_sub_network() throws Throwable {
        createCities(PARIS, LONDON, MADRID, ESSEN, MILAN, ALGIERS, NEW_YORK);

        getNetwork().addLink(PARIS, LONDON);
        getNetwork().addLink(PARIS, MADRID);
        getNetwork().addLink(PARIS, ESSEN);
        getNetwork().addLink(PARIS, MILAN);
        getNetwork().addLink(PARIS, ALGIERS);
        getNetwork().addLink(LONDON, ESSEN);
        getNetwork().addLink(LONDON, MADRID);
        getNetwork().addLink(ALGIERS, MADRID);
        getNetwork().addLink(MILAN, ESSEN);

        World.game.infectionCardsPiles.drawPile().clear();
        World.game.infectionCardsPiles.drawPile().add(InfectionCard.PARIS);
        World.game.infectionCardsPiles.drawPile().add(InfectionCard.LONDON);
        World.game.infectionCardsPiles.drawPile().add(InfectionCard.MADRID);
        World.game.infectionCardsPiles.drawPile().add(InfectionCard.MILAN);
        World.game.infectionCardsPiles.drawPile().add(InfectionCard.ALGIERS);
        World.game.infectionCardsPiles.drawPile().add(InfectionCard.NEW_YORK);
    }

    private void createCities(CityName... cityNames) {
        for (CityName cityName : cityNames) {
            getNetwork().addCity(cityName);
        }
    }

    @Then("^the cities should have the following infection levels:$")
    public void the_cities_should_have_the_following_infection_levels(
            List<CityInfectionLevel> expectedCityInfectionLevels) throws Throwable {

        expectedCityInfectionLevels.forEach(cityInfectionLevel -> Assertions
                .assertThat(AsyncAssertions.isTrueWithin(() ->
                                World.game.network.get(cityInfectionLevel.cityName).infectionLevelFor(Disease.BLUE).equals(cityInfectionLevel.getBlueLevel()),
                        1, TimeUnit.SECONDS))
                .as(cityInfectionLevel.cityName + " blueInfectionLevel").isTrue());

        expectedCityInfectionLevels.forEach(cityInfectionLevel -> Assertions
                .assertThat(AsyncAssertions.isTrueWithin(() ->
                                World.game.network.get(cityInfectionLevel.cityName).infectionLevelFor(Disease.BLACK).equals(cityInfectionLevel.getBlackLevel()),
                        1, TimeUnit.SECONDS))
                .as(cityInfectionLevel.cityName + " blackInfectionLevel").isTrue());
    }

    @And("^(.*) should be linked to (.*).$")
    public void cityShouldBeLinkedToLinkedCities(CityName cityName, List<CityName> linkedCities) throws Throwable {
        Assertions.assertThat(citiesLinkedTo(cityName).allMatch(linkedCities::contains)).isTrue();
    }

    private Stream<CityName> citiesLinkedTo(CityName cityName) {
        return getNetwork().citiesLinkedTo(cityName);
    }

    private Network getNetwork() {
        return World.game.network;
    }

    private static class CityInfectionLevel {

        final CityName cityName;

        final int blueLevel;
        final int blackLevel;

        public CityInfectionLevel(CityName cityName, int blueLevel, int blackLevel) {
            this.cityName = cityName;
            this.blueLevel = blueLevel;
            this.blackLevel = blackLevel;
        }

        private InfectionLevel getBlueLevel() {
            return from(blueLevel);
        }

        private InfectionLevel getBlackLevel() {
            return from(blackLevel);
        }
    }
}
