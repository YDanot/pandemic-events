package domain.infection.outbreak;

import domain.board.CityName;
import domain.game.TurnId;
import domain.infection.Disease;

public class OutbreakEvent {

    final CityName cityName;
    final TurnId turnId ;
    final Disease disease;

    OutbreakEvent(CityName cityName, Disease disease, TurnId turnId) {
        this.cityName = cityName;
        this.disease = disease;
        this.turnId = turnId;
    }
}
