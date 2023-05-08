package RMI_callback;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class TtyChatImpl extends UnicastRemoteObject implements TtyChat {
    private final List<TtyChatClient> occupants = new ArrayList<>();

    public TtyChatImpl() throws RemoteException {
    }

    public synchronized void enterRoom(TtyChatClient client) {
        occupants.add(client);
    }

    public synchronized void exitRoom(TtyChatClient client) {
        occupants.remove(client);
    }

    public synchronized void saySomething(
            String something, TtyChatClient speaker
    ) throws RemoteException {
        String message = speaker.name() + ": " + something;
        System.out.println(
                Thread.currentThread()
                        + ":Server: received '" + message + "'"
        );
        Iterator<TtyChatClient> iter = occupants.iterator();
        while (iter.hasNext()) {
            TtyChatClient client = iter.next();
            try {
                client.somethingSaid(message);
            } catch (RemoteException e) {
                System.out.println("Someone left");
                iter.remove();
            }
        }
    }
}
