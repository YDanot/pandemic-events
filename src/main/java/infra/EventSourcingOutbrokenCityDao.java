
package infra;

import java.util.List;
import java.util.stream.Collectors;

import domain.board.CityName;
import domain.game.TurnId;
import domain.infection.Disease;
import domain.infection.outbreak.OutbrokenCityFinder;

public class EventSourcingOutbrokenCityDao implements OutbrokenCityFinder {

	@Override
	public List<CityName> find(TurnId turnId, Disease disease) {
		return World.eventBus.outbreakEvents.stream()
				.filter(o -> o.turnId.equals(turnId))
				.map(o -> o.cityName).collect(Collectors.toList());
	}
}
