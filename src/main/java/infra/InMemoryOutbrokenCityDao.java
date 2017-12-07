
package infra;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import domain.board.CityName;
import domain.game.TurnId;
import domain.infection.Disease;
import domain.infection.outbreak.OutbrokenCityFinder;
import domain.infection.outbreak.OutbrokenCityMarker;

public class InMemoryOutbrokenCityDao implements OutbrokenCityFinder, OutbrokenCityMarker {

	private Map<TurnIdDisease, List<CityName>> outbreakCitiesByTurn = Maps.newHashMap();

	@Override
	public List<CityName> find(TurnId turnId, Disease disease) {
		return outbreakCitiesByTurn.computeIfAbsent(new TurnIdDisease(turnId, disease), k -> new ArrayList<>());
	}

	@Override
	public void mark(TurnId turnId, CityName cityName, Disease disease) {
		find(turnId, disease).add(cityName);
	}

	private class TurnIdDisease {

		private final TurnId turnId;

		private final Disease disease;

		private TurnIdDisease(TurnId turnId, Disease disease) {
			this.turnId = turnId;
			this.disease = disease;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof TurnIdDisease))
				return false;

			TurnIdDisease that = (TurnIdDisease) o;

			return turnId.equals(that.turnId) && disease == that.disease;
		}

		@Override
		public int hashCode() {
			int result = turnId.hashCode();
			result = 31 * result + disease.hashCode();
			return result;
		}
	}
}
