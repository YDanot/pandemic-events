package domain.actions;

import domain.game.Player;

import java.util.Optional;

public abstract class Action {

    public abstract Optional<ActionImpossible> act(Player p);
}
