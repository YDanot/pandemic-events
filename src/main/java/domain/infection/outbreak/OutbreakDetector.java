package domain.infection.outbreak;

import domain.game.TurnId;
import domain.board.CityName;
import domain.events.InfectionEvent;
import domain.events.OutbreakEvent;
import domain.infection.City;
import domain.infection.Disease;
import domain.infection.InfectionListener;
import infra.World;

public class OutbreakDetector implements InfectionListener {

    @Override
    public void onInfection(InfectionEvent infectionEvent) {
        if (shouldOutbreak(infectionEvent.disease, infectionEvent.city, infectionEvent.turnId)){
            outbreak(infectionEvent.disease, infectionEvent.city.name(), infectionEvent.turnId);
        }
    }

    private boolean shouldOutbreak(Disease disease, City city, TurnId turnId) {
        return diseaseOutbreakInfectionLevelReached(disease, city) && !alreadyOutbrokenInTurn(disease, city.name(), turnId);
    }

    private boolean diseaseOutbreakInfectionLevelReached(Disease disease, City city) {
        boolean diseaseOutbreakInfectionLevelReached = false;
        switch(disease){

        case BLUE:
            diseaseOutbreakInfectionLevelReached = city.blueOutbreakInfectionLevelReached();
            break;
        case BLACK:
            diseaseOutbreakInfectionLevelReached = city.blackOutbreakInfectionLevelReached();
            break;
        }
        return diseaseOutbreakInfectionLevelReached;
    }

    private boolean alreadyOutbrokenInTurn(Disease disease, CityName cityName, TurnId turnId) {
        return World.outbrokenCityFinder.find(turnId, disease).contains(cityName);
    }

    private void outbreak(Disease disease, CityName cityName, TurnId turnId) {
        World.outbrokenCityMarker.mark(turnId, cityName, disease);
        World.eventBus.publish(new OutbreakEvent(cityName, disease, turnId));
    }
}
