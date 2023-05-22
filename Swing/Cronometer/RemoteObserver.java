import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteObserver extends Remote {
    void inform(Object observable, Object updateMsg)
            throws RemoteException;
}

