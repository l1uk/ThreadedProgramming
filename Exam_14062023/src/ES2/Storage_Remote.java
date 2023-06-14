package ES2;

import ES1.Priority;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Storage_Remote extends UnicastRemoteObject implements PriorityStorage_Remote {

    static final int MAX_CAPACITY = 10; // numero massimo di messaggi per valore di priorità

    static final int REGISTRY_PORT = 1099;

    static final String SERVICE_NAME = "Storage";

    //mappa che ha come chiave la priorità e come valore una lista di messaggi gestita in FIFO
    private final Map<Integer, ArrayList<String>> messaggi = new HashMap<>();

    protected Storage_Remote() throws RemoteException {
    }

    public static void main(String[] args) throws RemoteException {
        System.out.println("Starting RMI server...");

        Registry r = LocateRegistry.createRegistry(REGISTRY_PORT);

        PriorityStorage_Remote service = new Storage_Remote();

        r.rebind(SERVICE_NAME, service);

        System.out.println("RMI server started!");

    }

    @Override
    public synchronized void insert(String s, Priority p) throws StorageFull, RemoteException {
        //inserisce s nel sistema di memorizzazione con
        //priorità p. Qualora sia stato raggiunto il numero massimo di stringhe a priorità p viene sollevata
        //l’eccezione StorageFull;

        messaggi.putIfAbsent(p.value, new ArrayList<>());// create a new stack if there is not one already
        if (messaggi.get(p.value).size() >= MAX_CAPACITY)
            throw new StorageFull();
        messaggi.get(p.value).add(s);
        notifyAll();

    }

    @Override
    public synchronized String get(Priority p) throws RemoteException {
        //restituisce, senza eliminarla, la stringa a priorità p memorizzata
        //da più tempo. Qualora non vi siano stringhe a priorità p, il metodo è sospensivo;

        // condizione sospensiva: non ci sono messaggi per la determinata priorita
        while (messaggi.get(p.value) == null || messaggi.get(p.value).size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return messaggi.get(p.value).get(0);

    }

    @Override
    public synchronized void remove(Priority p) throws StorageEmpty, RemoteException {
        //elimina la stringa a priorità p memorizzata da più tempo.
        //Qualora non vi siano stringhe a priorità p viene sollevata l’eccezione StorageEmpty,
        //segnalando il valore della priorità p;
        if (messaggi.get(p.value) == null || messaggi.get(p.value).size() == 0) {
            throw new StorageEmpty("Priorità " + p.value + " vuota!");
        }

        messaggi.get(p.value).remove(0); // rimuovi il primo elemento


    }

    @Override
    public synchronized int num(Priority p) throws RemoteException {
        //restituisce il numero di stringhe con priorità p presenti nel sistema
        return (messaggi.get(p.value) == null ?
                0 : (messaggi.get(p.value).size()));

    }
}
