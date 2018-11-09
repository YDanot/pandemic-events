package domain.treatment;

import domain.infection.Disease;

public interface Treatment {

    void treat();

    Disease disease();

}
