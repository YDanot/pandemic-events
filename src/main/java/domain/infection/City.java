package domain.infection;

import domain.board.CityName;

public class City {

	private final CityName cityName;
	private InfectionLevel blackLevel;
	private InfectionLevel blueLevel;

	public City(CityName cityName) {
		this.cityName = cityName;
		this.blueLevel = InfectionLevel.from(Disease.BLUE, 0);
		this.blackLevel = InfectionLevel.from(Disease.BLACK, 0);
	}

	public CityName name() {
		return cityName;
	}

	public void blackInfection(){
		blackLevel = blackLevel.increase();
	}

	public void blueInfection(){
		blueLevel = blueLevel.increase();
	}

	public InfectionLevel blueInfectionLevel() {
		return blueLevel;
	}

	public boolean blueOutbreakInfectionLevelReached() {
		return blueLevel.outbreakLevelReached();
	}

	public boolean blackOutbreakInfectionLevelReached() {
		return blackLevel.outbreakLevelReached();
	}

	public InfectionLevel blackInfectionLevel() {
		return this.blackLevel;
	}
}
