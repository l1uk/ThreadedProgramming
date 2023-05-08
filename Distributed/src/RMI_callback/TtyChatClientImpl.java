package RMI_callback;

import java.io.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TtyChatClientImpl
        extends UnicastRemoteObject implements TtyChatClient {
    private final String myName;

    public TtyChatClientImpl(String myName) throws RemoteException {
        this.myName = myName;
    }

    public String name() {
        return myName;
    }

    public void somethingSaid(String something) {
        System.out.println(something);
    }

    public static void main(String[] args) throws Exception {
        try (
                BufferedReader input =
                        new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("What is your name?");
            TtyChatClientImpl me =
                    new TtyChatClientImpl(input.readLine());
            Registry registry = LocateRegistry.getRegistry();
            TtyChat server = (TtyChat) registry.lookup("TtyChat");
            server.enterRoom(me);
            System.out.println("You can now chat in the room");
            while (true) {
                String s = input.readLine();
                if (s.equals("<quit>")) {
                    break;
                }
                server.saySomething(s, me);
            }
            server.exitRoom(me);
            UnicastRemoteObject.unexportObject(me, false);
        }
    }

}

