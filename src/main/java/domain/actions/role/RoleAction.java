package domain.actions.role;

import domain.actions.Action;
import domain.actions.ActionImpossible;
import domain.game.Player;
import domain.role.Role;

import java.util.Optional;

public abstract class RoleAction extends Action {

    private final Role role;

    public RoleAction(Role role) {
        this.role = role;
    }

    private Role role() {
        return role;
    }

    @Override
    public Optional<ActionImpossible> act(Player p) {
        if (!p.role().equals(role())) {
            return Optional.of(ActionImpossible.WRONG_ROLE);

        }
        return specialAct(p);
    }

    protected abstract Optional<ActionImpossible> specialAct(Player p);
}
