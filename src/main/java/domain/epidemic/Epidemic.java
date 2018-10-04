package domain.epidemic;

import domain.game.TurnId;
import domain.infection.InfectionEvent;
import domain.infection.cards.InfectionCard;
import domain.network.CityName;
import infra.World;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Epidemic implements EpidemicListener {

    @Override
    public void onEpidemic() {
        increaseInfectionRate();
        infectLastDrawCardThreeTimes();
        intensifyInfections();
    }

    private void increaseInfectionRate() {
        World.game.infectionRateTrack.moveUp();
    }

    private void infectLastDrawCardThreeTimes() {
        InfectionCard infectionCard = World.game.infectionCardsPiles.drawBottom();
        CityName cityName = CityName.valueOf(infectionCard.name());
        TurnId turnId = new TurnId();
        infect(infectionCard, cityName, turnId);
        infect(infectionCard, cityName, turnId);
        infect(infectionCard, cityName, turnId);
    }

    private void infect(InfectionCard infectionCard, CityName cityName, TurnId turnId) {
        World.eventBus.publish(new InfectionEvent(infectionCard.disease(), cityName, turnId,
                World.game.network.get(cityName).infectionLevelFor(infectionCard.disease())));
    }

    private void intensifyInfections() {
        placeOnTopOfDrawPile(shuffleDiscardPile());
    }

    private void placeOnTopOfDrawPile(List<InfectionCard> discardPile) {
        Stack<InfectionCard> drawPile = World.game.infectionCardsPiles.drawPile();
        discardPile.forEach(drawPile::push);
        discardPile.clear();
    }

    private List<InfectionCard> shuffleDiscardPile() {
        List<InfectionCard> discardPile = World.game.infectionCardsPiles.discardPile();
        Collections.shuffle(discardPile);
        return discardPile;
    }
}
