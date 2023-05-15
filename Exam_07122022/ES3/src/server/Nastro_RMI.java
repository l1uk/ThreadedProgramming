package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

public class Nastro_RMI extends UnicastRemoteObject implements NastroRemote {
    private static final long serialVersionUID = 1L;
    LinkedList<String> nastro;
    int currentPosition = 0;

    protected Nastro_RMI() throws RemoteException {
        super();
        nastro = new LinkedList<>();
        currentPosition = 0;
    }

    @Override
    public void write(String s) throws noWrite, RemoteException {
        if (currentPosition < nastro.size()) {
            throw new noWrite();
        }
        nastro.addLast(s);
        currentPosition++;
    }

    @Override
    public String read() throws noRead, RemoteException {
        if (currentPosition >= nastro.size()) {
            throw new noRead();
        }
        return nastro.get(currentPosition++);
    }

    @Override
    public void rewind() throws RemoteException {
        currentPosition = 0;
    }

    @Override
    public boolean empty() throws RemoteException {
        return nastro.isEmpty();
    }

    public static int PORT = 1099;

    public static void main(String[] args) throws RemoteException {

        Registry r = LocateRegistry.createRegistry(PORT);
        NastroRemote stub = new Nastro_RMI();
        r.rebind("Nastro", stub);
        System.out.println("Nastro ready");

    }
}
