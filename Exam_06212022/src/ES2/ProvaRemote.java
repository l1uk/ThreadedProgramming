package ES2;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ProvaRemote {
    public static void main(String args[]) throws InterruptedException, RemoteException, NotBoundException {

        Registry r = LocateRegistry.getRegistry(1099);

        SistemaBancaRemoteInterface gest = (SistemaBancaRemoteInterface) r.lookup("GestioneRemota");
        new Thread(
                new Correntista_Remote("iban_a", "iban_b", gest, 'a')
        ).start();
        Thread.sleep(800);
        new Thread(
                new Correntista_Remote("iban_b", gest, 'b')
        ).start();

    }
}
