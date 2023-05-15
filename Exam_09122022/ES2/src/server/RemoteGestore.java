package server;

import common.ConsumatoreMsgInterface_RMI;
import common.Msg_RMI;
import common.ProduttoreMsgInterface_RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteGestore extends Remote {
    public void signUp(int id) throws ConsumatoreMsgInterface_RMI.GiaRegistrato, RemoteException;

    public Msg_RMI receive(int id) throws InterruptedException, ConsumatoreMsgInterface_RMI.ConsumatoreSconosciuto, RemoteException;

    public void send(Msg_RMI m) throws ProduttoreMsgInterface_RMI.DestinatarioPieno, RemoteException;
}
