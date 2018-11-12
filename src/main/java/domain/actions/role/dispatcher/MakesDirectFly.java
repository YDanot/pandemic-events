package domain.actions.role.dispatcher;

import domain.actions.movement.DirectFly;
import domain.game.Player;
import domain.network.CityName;
import domain.role.Role;
import infra.World;

public class MakesDirectFly extends DispatcherAction {

    private final CityName destination;
    private Role pawn;

    public MakesDirectFly(Agreement agreement, Role pawn, CityName destination) {
        super(agreement);
        this.pawn = pawn;
        this.destination = destination;
    }

    @Override
    public void specialAct(Player actor) {
        DirectFly directFly = new DirectFly(destination);
        directFly.fly(Player.as(pawn), World.game.playerHands.handOf(actor));
    }

}
