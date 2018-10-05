
package domain.board;

import com.google.common.collect.Lists;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import domain.actions.basics.PawnLocations;
import domain.cube.CubeBank;
import domain.game.Game;
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
    public void the_occident_sub_network() throws Throwable {
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

        World.create(new Game(network, new CubeBank(), new OutbreakCounter(), new CureMarkerArea(), new PawnLocations(), new ResearchStations(), new InfectionCardsPiles(), new InfectionRateTrack(), new PlayerCardsPiles(),
                Lists.newArrayList()));
    }

    Network standard_network() {
        Network network = createCities(CityName.values());

        network.addLink(SAN_FRANCISCO, LOS_ANGELES);
        network.addLink(SAN_FRANCISCO, CHICAGO);
        network.addLink(SAN_FRANCISCO, TOKYO);
        network.addLink(SAN_FRANCISCO, OSAKA);

        network.addLink(LOS_ANGELES, MEXICO_CITY);
        network.addLink(LOS_ANGELES, CHICAGO);
        network.addLink(LOS_ANGELES, SYDNEY);

        network.addLink(MEXICO_CITY, CHICAGO);
        network.addLink(MEXICO_CITY, MIAMI);
        network.addLink(MEXICO_CITY, BOGOTA);
        network.addLink(MEXICO_CITY, LIMA);

        network.addLink(LIMA, BOGOTA);
        network.addLink(LIMA, SANTIAGO);

        network.addLink(BUENOS_ARIES, BOGOTA);
        network.addLink(BUENOS_ARIES, SAO_PAULO);

        network.addLink(SAO_PAULO, BOGOTA);
        network.addLink(SAO_PAULO, LAGOS);
        network.addLink(SAO_PAULO, MADRID);

        network.addLink(LAGOS, KINSHASA);
        network.addLink(LAGOS, KHARTOUM);

        network.addLink(KINSHASA, KHARTOUM);
        network.addLink(KINSHASA, JOHANNESBURG);

        network.addLink(JOHANNESBURG, KHARTOUM);

        network.addLink(BOGOTA, MIAMI);

        network.addLink(MIAMI, ATLANTA);
        network.addLink(MIAMI, WASHINGTON);

        network.addLink(ATLANTA, WASHINGTON);
        network.addLink(ATLANTA, CHICAGO);

        network.addLink(CHICAGO, MONTREAL);

        network.addLink(MONTREAL, NEW_YORK);
        network.addLink(MONTREAL, WASHINGTON);

        network.addLink(NEW_YORK, WASHINGTON);
        network.addLink(NEW_YORK, LONDON);
        network.addLink(NEW_YORK, MADRID);

        network.addLink(LONDON, MADRID);
        network.addLink(LONDON, ESSEN);
        network.addLink(LONDON, PARIS);

        network.addLink(MADRID, PARIS);
        network.addLink(MADRID, ALGIERS);

        network.addLink(ALGIERS, CAIRO);
        network.addLink(ALGIERS, ISTANBUL);
        network.addLink(ALGIERS, PARIS);

        network.addLink(CAIRO, KHARTOUM);
        network.addLink(CAIRO, RIYADH);
        network.addLink(CAIRO, BAGHDAD);
        network.addLink(CAIRO, ISTANBUL);

        network.addLink(ISTANBUL, BAGHDAD);
        network.addLink(ISTANBUL, MOSCOW);
        network.addLink(ISTANBUL, ST_PETERSBURG);
        network.addLink(ISTANBUL, MILAN);

        network.addLink(MILAN, PARIS);
        network.addLink(MILAN, ESSEN);

        network.addLink(ESSEN, PARIS);
        network.addLink(ESSEN, ST_PETERSBURG);

        network.addLink(ST_PETERSBURG, MOSCOW);

        network.addLink(MOSCOW, TEHRAN);

        network.addLink(TEHRAN, BAGHDAD);
        network.addLink(TEHRAN, KARACHI);
        network.addLink(TEHRAN, DELHI);

        network.addLink(KARACHI, BAGHDAD);
        network.addLink(KARACHI, RIYADH);
        network.addLink(KARACHI, DELHI);
        network.addLink(KARACHI, MUMBAI);

        network.addLink(MUMBAI, CHENNAI);
        network.addLink(MUMBAI, DELHI);

        network.addLink(CHENNAI, DELHI);
        network.addLink(CHENNAI, BANGKOK);
        network.addLink(CHENNAI, JAKARTA);
        network.addLink(CHENNAI, KOLKATA);

        network.addLink(KOLKATA, DELHI);
        network.addLink(KOLKATA, BANGKOK);
        network.addLink(KOLKATA, HONG_KONG);

        network.addLink(HONG_KONG, BANGKOK);
        network.addLink(HONG_KONG, HO_CHI_MINH_CITY);
        network.addLink(HONG_KONG, TAIPEI);
        network.addLink(HONG_KONG, MANILA);
        network.addLink(HONG_KONG, SHANGHAI);

        network.addLink(SHANGHAI, HONG_KONG);
        network.addLink(SHANGHAI, BEIJING);
        network.addLink(SHANGHAI, SEOUL);
        network.addLink(SHANGHAI, TOKYO);
        network.addLink(SHANGHAI, TAIPEI);

        network.addLink(BEIJING, SEOUL);

        network.addLink(SEOUL, TOKYO);
        network.addLink(TOKYO, OSAKA);

        network.addLink(OSAKA, TAIPEI);

        network.addLink(TAIPEI, MANILA);
        network.addLink(MANILA, HO_CHI_MINH_CITY);
        network.addLink(MANILA, SYDNEY);
        network.addLink(HO_CHI_MINH_CITY, BANGKOK);
        network.addLink(HO_CHI_MINH_CITY, JAKARTA);

        network.addLink(SYDNEY, JAKARTA);

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
