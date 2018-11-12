package domain.actions.role.dispatcher;

import domain.actions.movement.CharterFly;
import domain.game.Player;
import domain.network.CityName;
import domain.role.Role;
import infra.World;

public class MakesCharterFly extends DispatcherAction {

    private final CityName destination;
    private Role pawn;

    public MakesCharterFly(Agreement agreement, Role pawn, CityName destination) {
        super(agreement);
        this.pawn = pawn;
        this.destination = destination;
    }

    @Override
    public void specialAct(Player p) {
        CharterFly charter = new CharterFly(destination);
        charter.fly(Player.as(pawn), World.game.playerHands.handOf(p));
    }

}
