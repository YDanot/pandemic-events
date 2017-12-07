package domain;

import domain.events.InfectionEvent;
import infra.World;

public enum Disease implements InfectionListener {
	BLUE;

	@Override
	public void onInfection(InfectionEvent infectionEvent) {
		infectionEvent.disease.infect(infectionEvent.cityName);
	}

	public void infect(CityName cityName){
		City city = World.getNetwork().get(cityName);
		city.infect(this);
	}
}
