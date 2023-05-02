package RMI.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class HelloImpl extends UnicastRemoteObject implements Hello {
    protected HelloImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Hello!";
    }

    @Override
    public String sayHello(Person p) throws RemoteException {
        return "Hello " + p.getName() + "!";
    }

    public static void main(String[] args) throws Exception {
        Registry reg = LocateRegistry.createRegistry(1099);
        HelloImpl obj = new HelloImpl();
        reg.rebind("HelloServer", obj);
        System.out.println("Server ready");
    }
}
