package domain.infection;

import domain.board.CityName;
import domain.game.TurnId;

public class InfectionEvent {

    public final CityName cityName;
    public final Disease disease;
    public final TurnId turnId;

    public InfectionEvent(Disease disease, CityName cityName, TurnId turnId) {
        this.cityName = cityName;
        this.disease = disease;
        this.turnId = turnId;
    }

}
