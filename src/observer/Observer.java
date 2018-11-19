package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gdepina on 12/11/17.
 */
public class Observer {
    private List<Observable> observables = new ArrayList<Observable>();


    public void register(Observable obs) {
        this.observables.add(obs);
    }

    public void remove(Observable obs) {
        this.observables.remove(obs);
    }

    public void notifyObservables() {
        for (Observable obs: this.observables) {
            obs.update();
        }
    }
}
