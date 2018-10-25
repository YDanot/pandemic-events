package domain.actions.role;

import domain.game.Player;
import domain.role.Role;

public abstract class DispatcherAction extends RoleAction {

    DispatcherAction() {
        super(Role.DISPATCHER);
    }

    @Override
    public abstract void specialAct(Player p);
}
