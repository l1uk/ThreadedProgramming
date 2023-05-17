package client;

import server.GestioneParcheggioRemote;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Sistema {

    static int NUM_PIANI = 3;
    static int NUM_STALLI = 5;
    static int NUM_AUTOMOBILISTI = 20;

    public static void main(String[] args) throws RemoteException, NotBoundException {

        Registry reg = LocateRegistry.getRegistry(1099);

        GestioneParcheggioRemote gp = (GestioneParcheggioRemote) reg.lookup("GestioneParcheggio");

        gp.setPianiStalli(NUM_PIANI, NUM_STALLI);

        for (int i = 0; i < NUM_AUTOMOBILISTI; i++) {
            new Thread(new Client(gp, i)).start();
        }

    }
}