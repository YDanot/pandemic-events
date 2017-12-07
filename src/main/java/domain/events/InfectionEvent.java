package domain.events;

import static domain.infection.Disease.BLUE;

import domain.infection.City;
import domain.infection.Disease;
import domain.game.TurnId;

public class InfectionEvent {

    public final City city;
    public final Disease disease;
    public final TurnId turnId;

    public InfectionEvent(City city, TurnId turnId) {
        this.city = city;
        this.disease = BLUE;
        this.turnId = turnId;
    }

}
