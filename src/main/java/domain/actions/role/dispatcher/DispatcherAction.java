package domain.actions.role.dispatcher;

import domain.actions.ActionImpossible;
import domain.actions.role.RoleAction;
import domain.game.Player;
import domain.role.Role;

import java.util.Optional;

public abstract class DispatcherAction extends RoleAction {

    private final Agreement agreement;

    DispatcherAction(Agreement agreement) {
        super(Role.DISPATCHER);
        this.agreement = agreement;
    }

    @Override
    public Optional<ActionImpossible> act(Player p) {
        Optional<ActionImpossible> errors = Optional.empty();
        if (!agreement.agreed()) {
            errors = Optional.of(ActionImpossible.NO_AGREEMENT);
        }
        super.act(p);
        return errors;
    }

    @Override
    public abstract Optional<ActionImpossible> specialAct(Player p);
}
