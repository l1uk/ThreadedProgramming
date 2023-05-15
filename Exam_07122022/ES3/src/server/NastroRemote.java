package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NastroRemote extends Remote {
    void write(String s) throws noWrite, RemoteException;

    String read() throws noRead, RemoteException;

    void rewind() throws RemoteException;

    boolean empty() throws RemoteException;

    class noWrite extends Exception {
    }

    class noRead extends Exception {
    }
}
