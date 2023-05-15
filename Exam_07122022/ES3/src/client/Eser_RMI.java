package client;

import server.NastroRemote;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Eser_RMI {
    public static int PORT = 1099;
    public static int NUM_USERS = 10;

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry r = LocateRegistry.getRegistry(PORT);
        NastroRemote n = (NastroRemote) r.lookup("Nastro");
        for (int i = 0; i < NUM_USERS; i++) {
            new Thread(new User(n)).start();
        }

    }
}
