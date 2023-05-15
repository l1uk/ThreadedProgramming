package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConsumatoreMsgInterface_RMI extends Remote {
    void signUp(int id) throws GiaRegistrato, RemoteException;

    Msg_RMI receive(int id) throws InterruptedException, ConsumatoreSconosciuto, RemoteException;

    class GiaRegistrato extends Exception {
    }

    class ConsumatoreSconosciuto extends Exception {
    }
}
