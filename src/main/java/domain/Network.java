package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class Network {

    private final List<Link> links = new ArrayList<>();
    private final Map<CityName,City> cityMap = new HashMap<>();

    public void addLink(CityName cityName1, CityName cityName2){
        links.add(new Link(cityName1, cityName2));
    }

    public City addCity(CityName cityName1) {
        return cityMap.put(cityName1, new City(cityName1));
    }

    public Stream<CityName> citiesLinkedTo(CityName cityName){
        return links.stream()
                .filter(l -> l.contains(cityName))
                .map(l -> l.other(cityName).get());
    }

    public City get(CityName cityName) {
        return cityMap.get(cityName);
    }

    private class Link {
        private final CityName city1;
        private final CityName city2;

        private Link(CityName cityName1, CityName cityName2) {
            if (cityName1 == null || cityName2 == null){
                throw new NullPointerException("cities must be defined");
            }

            this.city1 = cityName1;
            this.city2 = cityName2;
        }

        private boolean contains(CityName cityName){
            return cityName == city1 || cityName == city2;
        }

        private Optional<CityName> other(CityName cityName){
            if (city1 == cityName){
                return Optional.of(city2);
            }
            else if (city2 == cityName){
                return Optional.of(city1);
            }
            return Optional.empty();
        }
    }
}
