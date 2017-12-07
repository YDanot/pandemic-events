package domain.infection;

import domain.events.InfectionEvent;

public class CityInfector implements InfectionListener{

	@Override
	public void onInfection(InfectionEvent infectionEvent) {
		infectionEvent.city.infect(infectionEvent.disease);
	}

}
