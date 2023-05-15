package produttore;

import server.RemoteGestore;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteProduttore extends Remote {
    public void start() throws RemoteException;

    public void setGestore(RemoteGestore g) throws RemoteException;
}
