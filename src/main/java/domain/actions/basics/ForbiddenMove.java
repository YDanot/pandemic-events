package domain.actions.basics;

import domain.actions.ActionImpossible;
import domain.network.CityName;
import domain.role.Role;
import infra.World;

public class ForbiddenMove extends ActionImpossible {

    ForbiddenMove(Role role, CityName to) {
        super("You cannot move from " + World.board.locations.locationsOf(role) + " to " + to + " as " + role);
    }
}
