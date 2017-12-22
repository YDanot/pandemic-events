
package domain.board;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import domain.cube.CubeBank;
import domain.infection.Disease;
import domain.infection.InfectionLevel;
import infra.World;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.stream.Stream;

import static domain.board.CityName.*;
import static domain.infection.InfectionLevel.from;

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
				.assertThat(World.network.get(cityInfectionLevel.cityName).infectionLevelFor(Disease.BLUE))
				.as(cityInfectionLevel.cityName + " blueInfectionLevel").isEqualTo(cityInfectionLevel.getBlueLevel()));

		expectedCityInfectionLevels.forEach(cityInfectionLevel -> Assertions
				.assertThat(World.network.get(cityInfectionLevel.cityName).infectionLevelFor(Disease.BLACK))
				.as(cityInfectionLevel.cityName + " blackInfectionLevel").isEqualTo(cityInfectionLevel.getBlackLevel()));
	}

	@And("^(.*) should be linked to ([^\"]*).$")
	public void cityShouldBeLinkedToLinkedCities(CityName cityName, List<CityName> linkedCities) throws Throwable {
		Assertions.assertThat(citiesLinkedTo(cityName).allMatch(linkedCities::contains)).isTrue();
	}

	public CubeBank getCubeBank() {
		return World.cubeBank;
	}

	private Stream<CityName> citiesLinkedTo(CityName cityName) {
		return getNetwork().citiesLinkedTo(cityName);
	}

	private Network getNetwork() {
		return World.network;
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
