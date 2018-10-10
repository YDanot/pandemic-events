package domain.actions.basics;


import domain.actions.ActionImpossible;
import domain.network.CityName;

public class ForbiddenMove extends ActionImpossible {

    public ForbiddenMove(CityName from, CityName to) {
        super("You cannot move from " + from + " to " + to);
    }
}
