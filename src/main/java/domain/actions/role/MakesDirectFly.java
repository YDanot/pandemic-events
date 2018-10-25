package domain.actions.role;

import domain.actions.basics.DirectFly;
import domain.game.Player;
import domain.network.CityName;
import domain.role.Role;
import infra.World;

public class MakesDirectFly extends DispatcherAction {

    private final CityName destination;
    private Role pawn;

    public MakesDirectFly(Role pawn, CityName destination) {
        this.pawn = pawn;
        this.destination = destination;
    }

    @Override
    public void specialAct(Player actor) {
        DirectFly directFly = new DirectFly(destination);
        directFly.fly(Player.as(pawn), World.game.playerHands.handOf(actor));
    }

}
