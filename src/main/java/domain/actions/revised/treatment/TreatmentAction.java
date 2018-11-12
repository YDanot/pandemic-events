package domain.actions.revised.treatment;

import domain.actions.ActionImpossible;
import domain.actions.revised.RevisedAction;
import domain.game.Player;
import domain.treatment.Treatment;

import java.util.Optional;

public class TreatmentAction extends RevisedAction {

    private final Treatment treatment;

    public TreatmentAction(Treatment treatment) {
        this.treatment = treatment;
    }

    @Override
    public Optional<ActionImpossible> act(Player player) {

        Optional<ActionImpossible> treatable = new Treatability(treatment.location(), treatment.disease()).treatable();
        if (!treatable.isPresent()) {
            treatment.treat();
        }
        return treatable;
    }
}
