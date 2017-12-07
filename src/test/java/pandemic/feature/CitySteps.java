
package pandemic.feature;

import static domain.board.CityName.ALGER;
import static domain.board.CityName.ESSEN;
import static domain.board.CityName.LONDON;
import static domain.board.CityName.MADRID;
import static domain.board.CityName.MILAN;
import static domain.board.CityName.NEW_YORK;
import static domain.board.CityName.PARIS;
import static domain.infection.InfectionLevel.from;

import java.util.List;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import domain.board.CityName;
import domain.board.Network;
import domain.infection.InfectionLevel;
import infra.World;

public class CitySteps {

	@Given("^the occident initial sub-network$")
	public void the_occident_sub_network() throws Throwable {
		createCities(PARIS, LONDON, MADRID, ESSEN, MILAN, ALGER, NEW_YORK);

		getNetwork().addLink(PARIS, LONDON);
		getNetwork().addLink(PARIS, MADRID);
		getNetwork().addLink(PARIS, ESSEN);
		getNetwork().addLink(PARIS, MILAN);
		getNetwork().addLink(PARIS, ALGER);
		getNetwork().addLink(LONDON, ESSEN);
		getNetwork().addLink(LONDON, MADRID);
		getNetwork().addLink(ALGER, MADRID);
		getNetwork().addLink(MILAN, ESSEN);
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
				.assertThat(World.network.get(cityInfectionLevel.cityName).infectionLevel())
				.as(cityInfectionLevel.cityName + " infectionLevel").isEqualTo(cityInfectionLevel.getLevel()));
	}

	@And("^(.*) should be linked to ([^\"]*).$")
	public void cityShouldBeLinkedToLinkedCities(CityName cityName, List<CityName> linkedCities) throws Throwable {
		Assertions.assertThat(citiesLinkedTo(cityName).allMatch(linkedCities::contains)).isTrue();
	}

	private static class CityInfectionLevel {

		final CityName cityName;

		final int level;

		public CityInfectionLevel(CityName cityName, int level) {
			this.cityName = cityName;
			this.level = level;
		}

		private InfectionLevel getLevel() {
			return from(level);
		}
	}

	private Stream<CityName> citiesLinkedTo(CityName cityName) {
		return getNetwork().citiesLinkedTo(cityName);
	}

	private Network getNetwork() {
		return World.network;
	}
}
