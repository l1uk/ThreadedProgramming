package ES2;

import ES1.Priority;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static ES2.Storage_Remote.REGISTRY_PORT;
import static ES2.Storage_Remote.SERVICE_NAME;

public class Utente_Remote implements Runnable {
    static final int NUM_OPERAZIONI = 5;
    final String id;
    PriorityStorage_Remote s;

    private void print(String msg) {
        synchronized (System.out) {
            System.out.println(msg);
        }
    }

    public Utente_Remote(String id) throws RemoteException, NotBoundException {
        this.id = id;

        print("User " + id + ", Starting RMI server lookup...");

        Registry r = LocateRegistry.getRegistry(REGISTRY_PORT);

        s = (PriorityStorage_Remote) r.lookup(SERVICE_NAME); // ogni thread ha il suo stub, anche se tutti i thread sono sullo stesso nodo

        print("RMI server found!");

    }

    @Override
    public void run() {
        // metodo identico all'utente del primo esercizio, con la sola aggiunta di RemoteException nel catch
        for (int i = 0; i < NUM_OPERAZIONI; i++) {
            try {
                double rand = Math.random();
                Priority p = new Priority((int) (Priority.MIN + Math.random() * Priority.MAX));
                Thread.sleep((long) (100 + Math.random() * 1000));

                if (rand < 0.5) {
                    String message = "MESSAGGIO DI PROVA con priorità " + p.value + ", da utente " + id;
                    s.insert(message, p);
                    print("Messaggio con priorità " + p.value + ", scritto da utente " + id + " : \"" + message + "\"");

                } else if (rand < 0.6) {
                    print("Numero di messaggi per priorità " + p.value + ", da utente " + id + " :" + s.num(p));
                } else if (rand < 0.8) {
                    s.remove(p);
                    System.err.println("Messaggio con priorità " + p.value + ", rimosso da utente " + id);
                } else {
                    print(id + " in coda per leggere messaggio con priorità " + p.value + "...");
                    print("Messaggio con priorità " + p.value + ", letto da utente " + id + " : \"" + s.get(p) + "\"");
                }

            } catch (PriorityStorage_Remote.StorageEmpty | PriorityStorage_Remote.StorageFull | InterruptedException |
                     RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
