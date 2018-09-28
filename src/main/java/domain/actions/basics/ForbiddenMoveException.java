package domain.actions.basics;


import domain.network.CityName;

public class ForbiddenMoveException extends RuntimeException {

    ForbiddenMoveException(CityName from, CityName to) {
        super("You cannot move from " + from + " to " + to);
    }
}
