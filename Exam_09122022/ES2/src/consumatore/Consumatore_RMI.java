package consumatore;

import common.ConsumatoreMsgInterface_RMI;
import common.Msg_RMI;
import server.RemoteGestore;

import java.rmi.RemoteException;

public class Consumatore_RMI implements Runnable  {

    Integer id;
    RemoteGestore gestoreMessaggi;

    public Consumatore_RMI(RemoteGestore gestoreMessaggi, Integer id) {
        this.gestoreMessaggi = gestoreMessaggi;
        this.id = id;
    }

    @Override
    public void run() {
        synchronized (System.out) {
            System.out.println("Consumatore " + id + " avviato!");
        }
        try {
            gestoreMessaggi.signUp(id);
        } catch (ConsumatoreMsgInterface_RMI.GiaRegistrato | RemoteException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                Thread.sleep((long) (100 + Math.random() * 10000));
                synchronized (System.out) {
                    System.out.println("#CONSUMATORE " + id + " waiting for message");
                }
                Msg_RMI m = gestoreMessaggi.receive(id);
                synchronized (System.out) {
                    System.out.println("#CONSUMATORE " + id + " Message: " + m.txt);
                }
            } catch (InterruptedException | ConsumatoreMsgInterface_RMI.ConsumatoreSconosciuto | RemoteException e) {
                e.printStackTrace();
            }
        }
    }

}
