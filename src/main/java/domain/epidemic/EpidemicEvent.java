package domain.epidemic;

import domain.game.TurnId;

public class EpidemicEvent {

    private TurnId turnId;

    public EpidemicEvent(TurnId turnId) {
        this.turnId = turnId;
    }

    public TurnId turnId() {
        return turnId;
    }
}
