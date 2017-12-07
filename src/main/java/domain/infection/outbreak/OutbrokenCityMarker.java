package domain.infection.outbreak;

import domain.game.TurnId;
import domain.board.CityName;

public interface OutbrokenCityMarker {

	void mark(TurnId turnId, CityName cityName);
}
