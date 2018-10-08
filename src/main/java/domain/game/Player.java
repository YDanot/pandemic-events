package domain.game;

import domain.role.Role;

public class Player {

    private final Role role;

    public Player(Role role) {
        this.role = role;
    }

    public static Player as(Role role) {
        return new Player(role);
    }

    public Role role() {
        return role;
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
