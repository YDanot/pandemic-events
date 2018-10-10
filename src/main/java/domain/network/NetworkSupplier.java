package domain.network;

import static domain.network.CityName.*;

class NetworkSupplier {

    static Network standard() {
        Network network = createCities(CityName.values());

        network.addLink(SAN_FRANCISCO, LOS_ANGELES);
        network.addLink(SAN_FRANCISCO, CHICAGO);
        network.addLink(SAN_FRANCISCO, TOKYO);
        network.addLink(SAN_FRANCISCO, OSAKA);

        network.addLink(LOS_ANGELES, MEXICO_CITY);
        network.addLink(LOS_ANGELES, CHICAGO);
        network.addLink(LOS_ANGELES, SYDNEY);

        network.addLink(MEXICO_CITY, CHICAGO);
        network.addLink(MEXICO_CITY, MIAMI);
        network.addLink(MEXICO_CITY, BOGOTA);
        network.addLink(MEXICO_CITY, LIMA);

        network.addLink(LIMA, BOGOTA);
        network.addLink(LIMA, SANTIAGO);

        network.addLink(BUENOS_ARIES, BOGOTA);
        network.addLink(BUENOS_ARIES, SAO_PAULO);

        network.addLink(SAO_PAULO, BOGOTA);
        network.addLink(SAO_PAULO, LAGOS);
        network.addLink(SAO_PAULO, MADRID);

        network.addLink(LAGOS, KINSHASA);
        network.addLink(LAGOS, KHARTOUM);

        network.addLink(KINSHASA, KHARTOUM);
        network.addLink(KINSHASA, JOHANNESBURG);

        network.addLink(JOHANNESBURG, KHARTOUM);

        network.addLink(BOGOTA, MIAMI);

        network.addLink(MIAMI, ATLANTA);
        network.addLink(MIAMI, WASHINGTON);

        network.addLink(ATLANTA, WASHINGTON);
        network.addLink(ATLANTA, CHICAGO);

        network.addLink(CHICAGO, MONTREAL);

        network.addLink(MONTREAL, NEW_YORK);
        network.addLink(MONTREAL, WASHINGTON);

        network.addLink(NEW_YORK, WASHINGTON);
        network.addLink(NEW_YORK, LONDON);
        network.addLink(NEW_YORK, MADRID);

        network.addLink(LONDON, MADRID);
        network.addLink(LONDON, ESSEN);
        network.addLink(LONDON, PARIS);

        network.addLink(MADRID, PARIS);
        network.addLink(MADRID, ALGIERS);

        network.addLink(ALGIERS, CAIRO);
        network.addLink(ALGIERS, ISTANBUL);
        network.addLink(ALGIERS, PARIS);

        network.addLink(CAIRO, KHARTOUM);
        network.addLink(CAIRO, RIYADH);
        network.addLink(CAIRO, BAGHDAD);
        network.addLink(CAIRO, ISTANBUL);

        network.addLink(ISTANBUL, BAGHDAD);
        network.addLink(ISTANBUL, MOSCOW);
        network.addLink(ISTANBUL, ST_PETERSBURG);
        network.addLink(ISTANBUL, MILAN);

        network.addLink(MILAN, PARIS);
        network.addLink(MILAN, ESSEN);

        network.addLink(ESSEN, PARIS);
        network.addLink(ESSEN, ST_PETERSBURG);

        network.addLink(ST_PETERSBURG, MOSCOW);

        network.addLink(MOSCOW, TEHRAN);

        network.addLink(TEHRAN, BAGHDAD);
        network.addLink(TEHRAN, KARACHI);
        network.addLink(TEHRAN, DELHI);

        network.addLink(KARACHI, BAGHDAD);
        network.addLink(KARACHI, RIYADH);
        network.addLink(KARACHI, DELHI);
        network.addLink(KARACHI, MUMBAI);

        network.addLink(MUMBAI, CHENNAI);
        network.addLink(MUMBAI, DELHI);

        network.addLink(CHENNAI, DELHI);
        network.addLink(CHENNAI, BANGKOK);
        network.addLink(CHENNAI, JAKARTA);
        network.addLink(CHENNAI, KOLKATA);

        network.addLink(KOLKATA, DELHI);
        network.addLink(KOLKATA, BANGKOK);
        network.addLink(KOLKATA, HONG_KONG);

        network.addLink(HONG_KONG, BANGKOK);
        network.addLink(HONG_KONG, HO_CHI_MINH_CITY);
        network.addLink(HONG_KONG, TAIPEI);
        network.addLink(HONG_KONG, MANILA);
        network.addLink(HONG_KONG, SHANGHAI);

        network.addLink(SHANGHAI, HONG_KONG);
        network.addLink(SHANGHAI, BEIJING);
        network.addLink(SHANGHAI, SEOUL);
        network.addLink(SHANGHAI, TOKYO);
        network.addLink(SHANGHAI, TAIPEI);

        network.addLink(BEIJING, SEOUL);

        network.addLink(SEOUL, TOKYO);
        network.addLink(TOKYO, OSAKA);

        network.addLink(OSAKA, TAIPEI);

        network.addLink(TAIPEI, MANILA);
        network.addLink(MANILA, HO_CHI_MINH_CITY);
        network.addLink(MANILA, SYDNEY);
        network.addLink(HO_CHI_MINH_CITY, BANGKOK);
        network.addLink(HO_CHI_MINH_CITY, JAKARTA);

        network.addLink(SYDNEY, JAKARTA);

        return network;
    }

    private static Network createCities(CityName... cityNames) {
        Network network = new Network();
        for (CityName cityName : cityNames) {
            network.addCity(cityName);
        }
        return network;
    }

}
