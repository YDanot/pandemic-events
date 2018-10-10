
package domain.board;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import domain.cube.CubeBank;
import domain.game.Game;
import domain.game.Players;
import domain.infection.Disease;
import domain.infection.InfectionLevel;
import domain.infection.cards.InfectionCardsPiles;
import domain.infection.outbreak.OutbreakCounter;
import domain.infection.rate.InfectionRateTrack;
import domain.network.CityName;
import domain.network.Network;
import domain.player.cards.PlayerCardsPiles;
import domain.researchstation.ResearchStations;
import domain.treatment.cure.CureMarkerArea;
import infra.World;
import org.assertj.core.api.Assertions;
import org.mockito.Mockito;
import run.AsyncAssertions;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static domain.infection.InfectionLevel.from;
import static domain.network.CityName.*;


public class CitySteps {

    private boolean occidentSubNetworkUsed;

    @After
    public void tearDown() {
        occidentSubNetworkUsed = false;
    }

    @Given("^the occident initial sub-network$")
    public Network the_occident_sub_network() throws Throwable {
        Network network = createCities(PARIS, LONDON, MADRID, ESSEN, MILAN, ALGIERS, NEW_YORK);

        network.addLink(PARIS, LONDON);
        network.addLink(PARIS, MADRID);
        network.addLink(PARIS, ESSEN);
        network.addLink(PARIS, MILAN);
        network.addLink(PARIS, ALGIERS);
        network.addLink(LONDON, ESSEN);
        network.addLink(LONDON, MADRID);
        network.addLink(ALGIERS, MADRID);
        network.addLink(MILAN, ESSEN);
        occidentSubNetworkUsed = true;

        Board board = new Board(network, new CubeBank(), new OutbreakCounter(), new CureMarkerArea(), new InfectionCardsPiles(), new InfectionRateTrack(), Mockito.any(PawnLocations.class), new ResearchStations(), new PlayerCardsPiles());
        World.create(board, new Game(Mockito.any(Players.class), Game.Level.INTRODUCTION));
        return network;
    }

    private Network createCities(CityName... cityNames) {
        Network network = new Network();
        for (CityName cityName : cityNames) {
            network.addCity(cityName);
        }
        return network;
    }

    @Then("^the cities should have the following infection levels:$")
    public void the_cities_should_have_the_following_infection_levels(
            List<CityInfectionLevel> expectedCityInfectionLevels) throws Throwable {

        expectedCityInfectionLevels.forEach(cityInfectionLevel -> Assertions
                .assertThat(AsyncAssertions.isTrueWithin(() ->
                                World.board.network.get(cityInfectionLevel.cityName).infectionLevelFor(Disease.BLUE).equals(cityInfectionLevel.getBlueLevel()),
                        1, TimeUnit.SECONDS))
                .as(cityInfectionLevel.cityName + " blueInfectionLevel").isTrue());

        expectedCityInfectionLevels.forEach(cityInfectionLevel -> Assertions
                .assertThat(AsyncAssertions.isTrueWithin(() ->
                                World.board.network.get(cityInfectionLevel.cityName).infectionLevelFor(Disease.BLACK).equals(cityInfectionLevel.getBlackLevel()),
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
        return World.board.network;
    }

    @Then("^occident initial sub-network should be used$")
    public void occidentInitialSubNetworkShouldBeUsed() throws Throwable {
        Assertions.assertThat(occidentSubNetworkUsed).isTrue();
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
