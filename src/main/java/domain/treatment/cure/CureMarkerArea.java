package domain.treatment.cure;

import domain.infection.Disease;
import infra.World;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CureMarkerArea implements CureDiscoveringListener, EradicationListener {

    private Map<Disease, TreatmentState> cures = initCures();

    private HashMap<Disease, TreatmentState> initCures() {
        HashMap<Disease, TreatmentState> cures = new HashMap<>();
        Arrays.stream(Disease.values()).forEach(d -> cures.put(d, TreatmentState.ACTIVE));
        return cures;
    }

    private void markAsCured(Disease disease) {
        cures.put(disease, TreatmentState.CURED);
    }

    private void markAsEradicated(Disease disease) {
        cures.put(disease, TreatmentState.ERADICATED);
    }

    public boolean hasBeenCured(Disease disease) {
        return !cures.get(disease).equals(TreatmentState.ACTIVE);
    }

    public boolean hasBeenEradicated(Disease disease) {
        return cures.get(disease).equals(TreatmentState.ERADICATED);
    }

    @Override
    public void onCureDiscovered(CureDiscoveringEvent cureDiscoveringEvent) {
        markAsCured(cureDiscoveringEvent.disease);

        if (cures.keySet().stream().allMatch(this::hasBeenCured)) {
            World.eventBus.publish(new AllDiseaseCuredEvent());
        }
    }

    @Override
    public void onEradication(EradicationEvent eradicationEvent) {
        markAsEradicated(eradicationEvent.disease);
    }
}
