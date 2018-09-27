package domain.game;


import domain.network.CityName;

public class ForbiddenMoveException extends Exception {

    ForbiddenMoveException(CityName from, CityName to) {
        super("You cannot drive from " + from + " to " + to);
    }
}
