package domain.actions.role.dispatcher;

import domain.actions.ActionImpossible;
import domain.actions.movement.CharterFly;
import domain.game.Player;
import domain.network.CityName;
import domain.role.Role;
import infra.World;

import java.util.Optional;

public class MakesCharterFly extends DispatcherAction {

    private final CityName destination;
    private Role pawn;

    public MakesCharterFly(Agreement agreement, Role pawn, CityName destination) {
        super(agreement);
        this.pawn = pawn;
        this.destination = destination;
    }

    @Override
    public Optional<ActionImpossible> specialAct(Player p) {
        CharterFly charter = new CharterFly(destination);
        return charter.fly(Player.as(pawn), World.game.playerHands.handOf(p));
    }

}
