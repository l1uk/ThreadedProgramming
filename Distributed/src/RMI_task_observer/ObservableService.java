package RMI_task_observer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ObservableService extends Remote {
    public void addObserver(RemoteObserver o) throws RemoteException;

    public void detachObserver(RemoteObserver o) throws RemoteException;

    public void notifyObservers(Object msg) throws RemoteException;
}
