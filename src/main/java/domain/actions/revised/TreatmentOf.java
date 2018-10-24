package domain.actions.revised;

import domain.actions.ActionImpossible;
import domain.game.Player;
import domain.infection.Disease;
import domain.network.CityName;
import domain.treatment.TreatmentEvent;
import infra.World;

public class TreatmentOf implements RevisedAction {

    private Disease disease;

    public TreatmentOf(Disease disease) {
        this.disease = disease;
    }


    @Override
    public void act(Player player) {
        CityName cityName = World.board.locations.locationsOf(player.role());

        if (World.board.network.get(cityName).infectionLevelFor(disease).isZero()) {
            throw new ActionImpossible();
        }

        World.eventBus.publish(new TreatmentEvent(disease, cityName));
    }
}
