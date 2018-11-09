package domain.actions.revised;

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
