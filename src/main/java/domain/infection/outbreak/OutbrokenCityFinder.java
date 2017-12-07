package domain.infection.outbreak;

import domain.game.TurnId;
import domain.board.CityName;

import java.util.List;

public interface OutbrokenCityFinder {

	List<CityName> find(TurnId turnId);

}
