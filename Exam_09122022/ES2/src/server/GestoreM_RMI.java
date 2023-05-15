package server;

import common.ConsumatoreMsgInterface_RMI;
import common.Msg_RMI;
import common.ProduttoreMsgInterface_RMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class GestoreM_RMI extends UnicastRemoteObject implements ConsumatoreMsgInterface_RMI, ProduttoreMsgInterface_RMI, RemoteGestore {

    private static final long serialVersionUID = 1L;
    public static int MAX_MSG = 10;
    List<Integer> consumatoriRegistrati = new ArrayList<>();
    Map<Integer, Stack<Msg_RMI>> mapMsgConsumatore = new HashMap<>();

    protected GestoreM_RMI() throws RemoteException {
        super();
    }

    @Override
    public synchronized void signUp(int id) throws GiaRegistrato {
        if (consumatoriRegistrati.contains(id)) {
            throw new GiaRegistrato();
        }
        consumatoriRegistrati.add(id);
    }

    @Override
    public synchronized Msg_RMI receive(int id) throws InterruptedException, ConsumatoreSconosciuto {
        Msg_RMI result;
        if (!consumatoriRegistrati.contains(id)) {
            throw new ConsumatoreSconosciuto();
        }
        while (mapMsgConsumatore.get(id) == null || mapMsgConsumatore.get(id).size() == 0) {
            wait();
        }
        result = mapMsgConsumatore.get(id).pop();
        return result;
    }

    @Override
    public synchronized void send(Msg_RMI m) throws DestinatarioPieno {
        int numMsg;

        mapMsgConsumatore.computeIfAbsent(m.dest, k -> new Stack<>());
        numMsg = mapMsgConsumatore.get(m.dest).size();


        if (numMsg >= MAX_MSG) {
            throw new DestinatarioPieno(m.dest);
        } else {
            mapMsgConsumatore.get(m.dest).push(m);
        }
        notifyAll();
    }

    public static void main(String[] args) throws RemoteException {
        Registry reg = LocateRegistry.createRegistry(1099);
        RemoteGestore obj = new GestoreM_RMI();
        reg.rebind("Gestore_Messaggi", obj);
        System.out.println("Gestore_Messaggi ready");
    }
}
