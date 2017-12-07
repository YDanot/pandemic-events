package domain.infection.outbreak;

import domain.game.TurnId;
import domain.board.CityName;
import domain.infection.Disease;

public interface OutbrokenCityMarker {

	void mark(TurnId turnId, CityName cityName, Disease disease);
}
