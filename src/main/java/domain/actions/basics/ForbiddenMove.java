package domain.actions.basics;


import domain.network.CityName;

public class ForbiddenMove extends RuntimeException {

    public ForbiddenMove(CityName from, CityName to) {
        super("You cannot move from " + from + " to " + to);
    }
}
