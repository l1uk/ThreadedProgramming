package RMI_task_observer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public abstract class Subject implements ObservableService {

    List<RemoteObserver> observers = new ArrayList<>();

    public void addObserver(RemoteObserver o) throws RemoteException {
        observers.add(o);
    }

    public void detachObserver(RemoteObserver o) {
        observers.remove(o);
    }

    public void notifyObservers(Object msg) throws RemoteException {
        for (RemoteObserver obs : observers) {
            try {
                obs.inform(this, msg);
            } catch (RemoteException e) {
                System.out.println("Observer" + obs + " Removed from list");
                observers.remove(obs);
            }
        }
    }
}
