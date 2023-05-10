package RMI_task_observer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends Subject implements Computer {
    public static final int PORT = 7777;


    public static void main(String[] args) throws RemoteException {
        Server engine = new Server();
        Computer stub = (Computer) UnicastRemoteObject.exportObject(engine, 6667);
        Registry reg = LocateRegistry.createRegistry(PORT);
        reg.rebind("Computer", stub);
        System.out.println("Server ready");
    }


    @Override
    public <T> void startTask(Task<T> t) throws RemoteException {
        Thread th = new Thread(
                new NotifyCalculator<>(t, this)
        );
        th.start();
    }


}
