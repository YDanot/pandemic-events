package domain.treatment;

import domain.infection.Disease;
import domain.network.City;
import domain.network.CityName;
import infra.World;

public class FullTreatment implements Treatment {

    private final Disease disease;
    private final CityName cityName;

    public FullTreatment(Disease disease, CityName cityName) {
        this.disease = disease;
        this.cityName = cityName;
    }

    @Override
    public Disease disease() {
        return disease;
    }

    @Override
    public void treat() {
        City city = World.board.network.get(cityName);
        while (!city.isHealthyFor(disease)) {
            new BasicTreatment(disease, cityName).treat();
        }
    }

}
