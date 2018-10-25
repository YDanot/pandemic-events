package domain.actions.role;

import domain.actions.Action;
import domain.actions.ActionImpossible;
import domain.game.Player;
import domain.role.Role;

public abstract class RoleAction extends Action {

    private final Role role;

    public RoleAction(Role role) {
        this.role = role;
    }

    private Role role() {
        return role;
    }

    @Override
    public void act(Player p) {
        if (!p.role().equals(role())) {
            throw new ActionImpossible(this.getClass().getSimpleName() + " cannot be performed by " + p.role());
        }
        specialAct(p);
    }

    protected abstract void specialAct(Player p);
}
