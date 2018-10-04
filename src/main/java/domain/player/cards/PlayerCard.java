package domain.player.cards;


public enum PlayerCard {
    ALGIERS(PlayerCardColor.BLACK),
    BAGHDAD(PlayerCardColor.BLACK),
    CAIRO(PlayerCardColor.BLACK),
    CHENNAI(PlayerCardColor.BLACK),
    DELHI(PlayerCardColor.BLACK),
    ISTANBUL(PlayerCardColor.BLACK),
    KARACHI(PlayerCardColor.BLACK),
    KOLKATA(PlayerCardColor.BLACK),
    MOSCOW(PlayerCardColor.BLACK),
    MUMBAI(PlayerCardColor.BLACK),
    RIYADH(PlayerCardColor.BLACK),
    TEHRAN(PlayerCardColor.BLACK),

    ATLANTA(PlayerCardColor.BLUE),
    CHICAGO(PlayerCardColor.BLUE),
    ESSEN(PlayerCardColor.BLUE),
    LONDON(PlayerCardColor.BLUE),
    MADRID(PlayerCardColor.BLUE),
    MILAN(PlayerCardColor.BLUE),
    MONTREAL(PlayerCardColor.BLUE),
    NEW_YORK(PlayerCardColor.BLUE),
    PARIS(PlayerCardColor.BLUE),
    SAN_FRANCISCO(PlayerCardColor.BLUE),
    ST_PETERSBURG(PlayerCardColor.BLUE),
    WASHINGTON(PlayerCardColor.BLUE),

    BANGKOK(PlayerCardColor.RED),
    BEIJING(PlayerCardColor.RED),
    HO_CHI_MINH_CITY(PlayerCardColor.RED),
    HONG_KONG(PlayerCardColor.RED),
    JAKARTA(PlayerCardColor.RED),
    MANILA(PlayerCardColor.RED),
    OSAKA(PlayerCardColor.RED),
    SEOUL(PlayerCardColor.RED),
    SHANGHAI(PlayerCardColor.RED),
    SYDNEY(PlayerCardColor.RED),
    TAIPEI(PlayerCardColor.RED),
    TOKYO(PlayerCardColor.RED),

    BOGOTA(PlayerCardColor.YELLOW),
    BUENOS_ARIES(PlayerCardColor.YELLOW),
    JOHANNESBURG(PlayerCardColor.YELLOW),
    KHARTOUM(PlayerCardColor.YELLOW),
    KINSHASA(PlayerCardColor.YELLOW),
    LAGOS(PlayerCardColor.YELLOW),
    LIMA(PlayerCardColor.YELLOW),
    LOS_ANGELES(PlayerCardColor.YELLOW),
    MEXICO_CITY(PlayerCardColor.YELLOW),
    MIAMI(PlayerCardColor.YELLOW),
    SANTIAGO(PlayerCardColor.YELLOW),
    SAO_PAULO(PlayerCardColor.YELLOW),


    EPIDEMIC(PlayerCardColor.NONE);

    private PlayerCardColor color;

    PlayerCard(PlayerCardColor color) {
        this.color = color;
    }

    public PlayerCardColor color() {
        return color;
    }
}
