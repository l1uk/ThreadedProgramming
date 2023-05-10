package RMI_task_observer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ObservableService extends Remote {
    void addObserver(RemoteObserver o) throws RemoteException;

    void detachObserver(RemoteObserver o) throws RemoteException;

    void notifyObservers(Object msg) throws RemoteException;
}
