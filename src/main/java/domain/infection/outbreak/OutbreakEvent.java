package domain.infection.outbreak;

import domain.network.CityName;
import domain.game.TurnId;
import domain.infection.Disease;

public class OutbreakEvent {

    public final CityName cityName;
    public final TurnId turnId ;
    public final Disease disease;

    OutbreakEvent(CityName cityName, Disease disease, TurnId turnId) {
        this.cityName = cityName;
        this.disease = disease;
        this.turnId = turnId;
    }
}
