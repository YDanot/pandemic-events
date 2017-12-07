package domain;

import domain.events.OutbreakEvent;

public interface OutbreakListener {

    void onOutbreak(OutbreakEvent outbreakEvent);
}
