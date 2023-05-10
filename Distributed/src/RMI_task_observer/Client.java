package RMI_task_observer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client implements RemoteObserver {

    public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException {
        String host = args.length >= 1 ? args[0] : null;
        int port = args.length >= 2 ? Integer.parseInt(args[1]) : 7777;
        int digits = args.length >= 3 ? Integer.parseInt(args[2]) : 100;

        RemoteObserver client = new Client();
        RemoteObserver clientStub = (RemoteObserver)
                UnicastRemoteObject.exportObject(client, 5656);

        Registry reg = LocateRegistry.getRegistry(host, port);


        Computer server = (Computer) reg.lookup("Computer");


        ((ObservableService) reg.lookup("Computer")).addObserver(clientStub);

        Pi task = new Pi(digits);
        server.startTask(task);
        System.out.println("Task started");

    }

    @Override
    public void inform(Object observable, Object updateMsg) throws RemoteException {
        System.out.println("Got message: " + updateMsg);
    }

}
