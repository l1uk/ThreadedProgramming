package ES2;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Prova_Remote {
    static final int NUM_CLIENTS = 5;

    public static void main(String[] args) throws NotBoundException, RemoteException {
        for (int i = 0; i < NUM_CLIENTS; i++)
            new Thread(new Utente_Remote("UTENTE_" + i)).start();

    }
}