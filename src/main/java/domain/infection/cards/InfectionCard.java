package domain.infection.cards;

import domain.infection.Disease;

public enum InfectionCard {

    ALGIERS(Disease.BLACK),
    BAGHDAD(Disease.BLACK),
    CAIRO(Disease.BLACK),
    CHENNAI(Disease.BLACK),
    DELHI(Disease.BLACK),
    ISTANBUL(Disease.BLACK),
    KARACHI(Disease.BLACK),
    KOLKATA(Disease.BLACK),
    MOSCOW(Disease.BLACK),
    MUMBAI(Disease.BLACK),
    RIYADH(Disease.BLACK),
    TEHRAN(Disease.BLACK),

    ATLANTA(Disease.BLUE),
    CHICAGO(Disease.BLUE),
    ESSEN(Disease.BLUE),
    LONDON(Disease.BLUE),
    MADRID(Disease.BLUE),
    MILAN(Disease.BLUE),
    MONTREAL(Disease.BLUE),
    NEW_YORK(Disease.BLUE),
    PARIS(Disease.BLUE),
    SAN_FRANCISCO(Disease.BLUE),
    ST_PETERSBURG(Disease.BLUE),
    WASHINGTON(Disease.BLUE),

    BANGKOK(Disease.RED),
    BEIJING(Disease.RED),
    HO_CHI_MINH_CITY(Disease.RED),
    HONG_KONG(Disease.RED),
    JAKARTA(Disease.RED),
    MANILA(Disease.RED),
    OSAKA(Disease.RED),
    SEOUL(Disease.RED),
    SHANGHAI(Disease.RED),
    SYDNEY(Disease.RED),
    TAIPEI(Disease.RED),
    TOKYO(Disease.RED),

    BOGOTA(Disease.YELLOW),
    BUENOS_ARIES(Disease.YELLOW),
    JOHANNESBURG(Disease.YELLOW),
    KHARTOUM(Disease.YELLOW),
    KINSHASA(Disease.YELLOW),
    LAGOS(Disease.YELLOW),
    LIMA(Disease.YELLOW),
    LOS_ANGELES(Disease.YELLOW),
    MEXICO_CITY(Disease.YELLOW),
    MIAMI(Disease.YELLOW),
    SANTIAGO(Disease.YELLOW),
    SAO_PAULO(Disease.YELLOW);

    private Disease defaultDisease;

    InfectionCard(Disease defaultDisease) {
        this.defaultDisease = defaultDisease;
    }

    public Disease disease() {
        return defaultDisease;
    }
}
