package domain.infection.outbreak;

import domain.game.TurnId;
import domain.network.CityName;
import domain.infection.Disease;

import java.util.List;

public interface OutbrokenCityFinder {

	List<CityName> find(TurnId turnId, Disease disease);

}
