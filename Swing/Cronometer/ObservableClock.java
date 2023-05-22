import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ObservableClock extends Remote {
    void addObserver(RemoteObserver o) throws RemoteException;

    void detachObserver(RemoteObserver o) throws RemoteException;

    void notifyObservers(Object msg) throws RemoteException;

    private void incTime() throws RemoteException {

    }

    void reset() throws RemoteException;

    void started() throws RemoteException;

    void stop() throws RemoteException;


}
