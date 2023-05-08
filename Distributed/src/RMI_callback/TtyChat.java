package RMI_callback;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TtyChat extends Remote {
    void enterRoom(TtyChatClient client) throws RemoteException;

    void exitRoom(TtyChatClient client) throws RemoteException;

    void saySomething(String something, TtyChatClient speaker)
            throws RemoteException;
}