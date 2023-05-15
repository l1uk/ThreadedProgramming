import consumatore.Consumatore_RMI;
import produttore.RemoteProduttore;
import server.RemoteGestore;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Prova_RMI {

    static int numConsumatori = 5;
    static int PORT = 1099;

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry r = LocateRegistry.getRegistry("", PORT);
        RemoteGestore gestoreMessaggi = (RemoteGestore) r.lookup("Gestore_Messaggi");
        RemoteProduttore p = (RemoteProduttore) r.lookup("Produttore");

        p.setGestore(gestoreMessaggi);

        p.start();

        for (int i = 0; i < numConsumatori; i++) {
            new Thread(new Consumatore_RMI(gestoreMessaggi, i)).start();
        }

    }
}
