package domain.infection;

import infra.World;

public class CityInfector implements InfectionListener{

	@Override
	public void onInfection(InfectionEvent infectionEvent) {
		World.network.get(infectionEvent.cityName).infect(infectionEvent.disease);
	}

}
