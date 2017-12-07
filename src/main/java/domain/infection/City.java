package domain.infection;

import java.util.HashMap;
import java.util.Map;

import domain.board.CityName;

public class City {

	private Map<Disease, InfectionLevel> infections = new HashMap<>();
	private final CityName cityName;

	public City(CityName cityName) {
		this.cityName = cityName;
		for (Disease disease : Disease.values()) {
			infections.put(disease, InfectionLevel.from(0));
		}
	}

	public CityName name() {
		return cityName;
	}

	public void infect(Disease disease){
		infections.put(disease, infectionLevelFor(disease).increase());
	}

	public InfectionLevel infectionLevelFor(Disease disease) {
		return infections.get(disease);
	}

	public boolean outbreakInfectionLevelReached(Disease disease) {
		return infectionLevelFor(disease).outbreakLevelReached();
	}

}
