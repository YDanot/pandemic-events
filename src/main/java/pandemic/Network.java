package pandemic;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Network {

    private final List<Link> links = new ArrayList<>();

    public void addLink(City city1, City city2){
        links.add(new Link(city1, city2));
    }

    public Stream<City> citiesLinkedTo(City city){
        return links.stream()
                .filter(l -> l.contains(city))
                .map(l -> l.other(city).get());
    }

    private class Link {
        private final City city1;
        private final City city2;

        private Link(City city1, City city2) {
            if (city1 == null || city2 == null){
                throw new NullPointerException("cities must be defined");
            }

            this.city1 = city1;
            this.city2 = city2;
        }

        private boolean contains(City city){
            return city == city1 || city == city2;
        }

        private Optional<City> other(City city){
            if (city1 == city){
                return Optional.of(city2);
            }
            else if (city2 == city){
                return Optional.of(city1);
            }
            return Optional.empty();
        }
    }
}
