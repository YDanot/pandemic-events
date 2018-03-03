package domain.infection;

import domain.board.City;
import infra.World;

import static infra.World.cubeBank;

public class CityInfector implements InfectionListener{

	@Override
	public void onInfection(InfectionEvent infectionEvent) {
		City city = World.network.get(infectionEvent.cityName);
		InfectionLevel infectionLevel = city.infectionLevelFor(infectionEvent.disease);
		if (!infectionLevel.outbreakLevelReached()) {
			cubeBank.takeCube(infectionEvent.disease);
			city.infect(infectionEvent.disease);
		}
	}

}
