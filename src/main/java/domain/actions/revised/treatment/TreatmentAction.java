package domain.actions.revised.treatment;

import domain.actions.revised.RevisedAction;
import domain.game.Player;
import domain.treatment.Treatment;

public class TreatmentAction extends RevisedAction {

    private final Treatment treatment;

    public TreatmentAction(Treatment treatment) {
        this.treatment = treatment;
    }

    @Override
    public void act(Player player) {
        treatment.treat();
    }
}
