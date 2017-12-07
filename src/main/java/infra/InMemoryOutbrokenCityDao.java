package infra;

import com.google.common.collect.Maps;
import domain.board.CityName;
import domain.infection.outbreak.OutbrokenCityFinder;
import domain.infection.outbreak.OutbrokenCityMarker;
import domain.game.TurnId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InMemoryOutbrokenCityDao implements OutbrokenCityFinder, OutbrokenCityMarker {

	private Map<TurnId, List<CityName>> outbreakCitiesByTurn = Maps.newHashMap();

	@Override
	public List<CityName> find(TurnId turnId) {
		return outbreakCitiesByTurn.computeIfAbsent(turnId, k -> new ArrayList<>());
	}

	@Override
	public void mark(TurnId turnId, CityName cityName) {
		find(turnId).add(cityName);
	}
}
