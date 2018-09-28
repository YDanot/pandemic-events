
package infra;

import domain.game.TurnId;
import domain.infection.Disease;
import domain.infection.outbreak.OutbrokenCityFinder;
import domain.network.CityName;

import java.util.List;
import java.util.stream.Collectors;

public class EventSourcingOutbrokenCityDao implements OutbrokenCityFinder {

	@Override
	public List<CityName> find(TurnId turnId, Disease disease) {
		return World.eventBus.getOutbreakEvents().stream()
				.filter(o -> o.turnId.equals(turnId))
				.map(o -> o.cityName).collect(Collectors.toList());
	}
}
