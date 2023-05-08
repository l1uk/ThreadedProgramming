package RMI_callback;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;

public class Server {
    public static void main(String[] args) throws RemoteException {
        TtyChatImpl obj = new TtyChatImpl();
        Registry registry =
                LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        registry.rebind("TtyChat", obj);
        System.out.println("TtyChat Server bound in registry");
    }
}