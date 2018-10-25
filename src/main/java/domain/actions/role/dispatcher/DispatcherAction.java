package domain.actions.role.dispatcher;

import domain.actions.ActionImpossible;
import domain.actions.role.RoleAction;
import domain.game.Player;
import domain.role.Role;

public abstract class DispatcherAction extends RoleAction {

    private final Agreement agreement;

    DispatcherAction(Agreement agreement) {
        super(Role.DISPATCHER);
        this.agreement = agreement;
    }

    @Override
    public void act(Player p) {
        if (!agreement.agreed()) {
            throw new ActionImpossible("you don't have agreement of the player that you try to make move");
        }
        super.act(p);
    }

    @Override
    public abstract void specialAct(Player p);
}
