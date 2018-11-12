package domain.actions.role.dispatcher;

import domain.actions.ActionImpossible;
import domain.actions.movement.DriveOrFerry;
import domain.game.Player;
import domain.network.CityName;
import domain.role.Role;

import java.util.Optional;

public class MakesDrive extends DispatcherAction {

    private final CityName destination;
    private Role role;

    public MakesDrive(Agreement agreement, Role role, CityName destination) {
        super(agreement);
        this.role = role;
        this.destination = destination;
    }

    @Override
    public Optional<ActionImpossible> specialAct(Player p) {
        return Player.as(role).take(new DriveOrFerry(destination));
    }

}
