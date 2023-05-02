package RMI.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote {
    String sayHello() throws RemoteException;

    String sayHello(Person p) throws RemoteException;
}
