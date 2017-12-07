
package pandemic.feature;

import static domain.CityName.ALGER;
import static domain.CityName.ESSEN;
import static domain.CityName.LONDON;
import static domain.CityName.MADRID;
import static domain.CityName.MILAN;
import static domain.CityName.NEW_YORK;
import static domain.CityName.PARIS;
import static domain.InfectionLevel.from;

import java.util.List;
import java.util.stream.Stream;

import domain.City;
import org.assertj.core.api.Assertions;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import domain.CityName;
import domain.InfectionLevel;
import domain.Network;
import infra.World;

public class CitySteps {

	@After
	public void reset() {
		World.reset();
	}

	@Given("^the occident initial sub-network$")
	public void the_occident_sub_network() throws Throwable {
		createCities(PARIS, LONDON, MADRID, ESSEN, MILAN, ALGER, NEW_YORK);

		getNetwork().addLink(PARIS, LONDON);
		getNetwork().addLink(PARIS, MADRID);
		getNetwork().addLink(PARIS, ESSEN);
		getNetwork().addLink(PARIS, MILAN);
		getNetwork().addLink(PARIS, ALGER);
		getNetwork().addLink(LONDON, ESSEN);
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
				.assertThat(World.getNetwork().get(cityInfectionLevel.cityName).level()).isEqualTo(cityInfectionLevel.getLevel()));
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

		public InfectionLevel getLevel() {
			return from(level);
		}
	}

	private Stream<CityName> citiesLinkedTo(CityName cityName) {
		return getNetwork().citiesLinkedTo(cityName);
	}

	private Network getNetwork() {
		return World.getNetwork();
	}
}
