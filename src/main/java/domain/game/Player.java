package domain.game;

import domain.actions.Action;
import domain.role.Role;

public class Player {

    private final Role role;

    private Player(Role role) {
        this.role = role;
    }

    public static Player as(Role role) {
        return new Player(role);
    }

    public Role role() {
        return role;
    }

    public void take(Action action) {
        action.accept(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;

        return role == player.role;
    }

    @Override
    public int hashCode() {
        return role.hashCode();
    }
}
