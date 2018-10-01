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
        World.game.infectionRateTrack.moveUp();
        InfectionCard infectionCard = World.game.infectionCardsPiles.drawBottom();
        CityName cityName = CityName.valueOf(infectionCard.name());
        TurnId turnId = new TurnId();
        infect(infectionCard, cityName, turnId);
        infect(infectionCard, cityName, turnId);
        infect(infectionCard, cityName, turnId);

        List<InfectionCard> discardPile = World.game.infectionCardsPiles.discardPile();
        Stack<InfectionCard> drawPile = World.game.infectionCardsPiles.drawPile();
        Collections.shuffle(discardPile);
        discardPile.forEach(drawPile::push);
        discardPile.clear();

    }

    private void infect(InfectionCard infectionCard, CityName cityName, TurnId turnId) {
        World.eventBus.publish(new InfectionEvent(infectionCard.disease(), cityName, turnId,
                World.game.network.get(cityName).infectionLevelFor(infectionCard.disease())));
    }
}
