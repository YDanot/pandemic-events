package domain.board;

import domain.infection.Disease;
import domain.infection.InfectionLevel;

import java.util.HashMap;
import java.util.Map;

public class City {

	private final CityName cityName;
	private Map<Disease, InfectionLevel> infections = new HashMap<>();

	City(CityName cityName) {
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
