package domain.treatment;

import domain.infection.Disease;
import domain.network.CityName;

public interface Treatment {

    void treat();

    Disease disease();

    CityName location();
}
