package domain.actions.role;

import domain.actions.basics.DriveOrFerry;
import domain.game.Player;
import domain.network.CityName;
import domain.role.Role;

public class MakesDrive extends DispatcherAction {

    private final CityName destination;
    private Role role;

    public MakesDrive(Role role, CityName destination) {
        this.role = role;
        this.destination = destination;
    }

    @Override
    public void specialAct(Player p) {
        Player.as(role).take(new DriveOrFerry(destination));
    }

}
