package produttore;

import common.Msg_RMI;
import common.ProduttoreMsgInterface_RMI;
import server.RemoteGestore;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Produttore_RMI extends UnicastRemoteObject implements Runnable, RemoteProduttore {
    public static void main(String[] args) throws RemoteException {
        Registry r = LocateRegistry.getRegistry();
        RemoteProduttore obj = new Produttore_RMI();
        r.rebind("Produttore", obj);
        System.out.println("RemoteProduttore ready");
    }

    RemoteGestore gestoreMessaggi = null;

    public Produttore_RMI() throws RemoteException {
        super();
    }


    public Msg_RMI generaMessaggio() {
        int randomDest = (int) (Math.random() * 100);
        Msg_RMI m = new Msg_RMI(randomDest, "Messaggio di prova");
        return m;
    }

    @Override
    public void run() {
        synchronized (System.out) {
            System.out.println("Produttore avviato!");
        }
        while (true) {
            try {
                Thread.sleep((long) (100 + Math.random() * 150));
                gestoreMessaggi.send(generaMessaggio());
            } catch (InterruptedException | ProduttoreMsgInterface_RMI.DestinatarioPieno | RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void start() throws RemoteException {
        new Thread(this).start();
    }

    @Override
    public void setGestore(RemoteGestore g) throws RemoteException {
        this.gestoreMessaggi = g;
    }
}
