package domain.cure;

import domain.infection.Disease;
import infra.World;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CureMarkerArea implements CureDiscoveringListener {

    private Map<Disease, Boolean> cures = initCures();

    private HashMap<Disease, Boolean> initCures() {
        HashMap<Disease, Boolean> cures = new HashMap<>();
        Arrays.stream(Disease.values()).forEach(d->cures.put(d,false));
        return cures;
    }

    private void markAsCured(Disease disease){
        cures.put(disease,true);
    }

    public boolean isCured(Disease disease){
        return cures.get(disease);
    }

    @Override
    public void onCureDiscovered(CureDiscoveringEvent cureDiscoveringEvent) {
        markAsCured(cureDiscoveringEvent.disease);

        if (cures.keySet().stream().allMatch(this::isCured)){
            World.eventBus.publish(new AllDiseaseCuredEvent());
        }
    }
}
