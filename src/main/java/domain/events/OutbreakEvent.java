package domain.events;

import domain.board.CityName;
import domain.game.TurnId;

public class OutbreakEvent {

    public final CityName cityName;
    public final TurnId turnId ;

    public OutbreakEvent(CityName cityName, TurnId turnId) {
        this.cityName = cityName;
        this.turnId = turnId;
    }
}
