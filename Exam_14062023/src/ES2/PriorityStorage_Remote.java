package ES2;

import ES1.Priority;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PriorityStorage_Remote extends Remote {
    void insert(String s, Priority p) throws StorageFull, RemoteException;

    String get(Priority p) throws RemoteException;

    void remove(Priority p) throws StorageEmpty, RemoteException;

    int num(Priority p) throws RemoteException;

    class StorageFull extends Exception {
    }

    class StorageEmpty extends Exception {
        public StorageEmpty(String message) {
            super(message);
        }
    }
}