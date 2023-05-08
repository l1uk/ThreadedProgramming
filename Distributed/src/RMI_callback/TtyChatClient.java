package RMI_callback;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TtyChatClient extends Remote {
    String name() throws RemoteException;

    void somethingSaid(String something) throws RemoteException;
}

