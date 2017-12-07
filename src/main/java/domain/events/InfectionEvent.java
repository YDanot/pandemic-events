package domain.events;

import domain.game.TurnId;
import domain.infection.City;
import domain.infection.Disease;

public class InfectionEvent {

    public final City city;
    public final Disease disease;
    public final TurnId turnId;

    public InfectionEvent(Disease disease, City city, TurnId turnId) {
        this.city = city;
        this.disease = disease;
        this.turnId = turnId;
    }

}
