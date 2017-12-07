package domain.infection;

import domain.board.CityName;
import domain.events.InfectionEvent;
import infra.World;

public enum Disease implements InfectionListener {
	BLUE {
		@Override public void infect(CityName cityName) {
			City city = World.network.get(cityName);
			city.blueInfection();
		}
	},
	BLACK {
		@Override public void infect(CityName cityName) {
			City city = World.network.get(cityName);
			city.blackInfection();
		}
	};

	@Override
	public void onInfection(InfectionEvent infectionEvent) {
		infectionEvent.disease.infect(infectionEvent.city.name());
	}

	public abstract void infect(CityName cityName);
}
