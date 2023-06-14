package ES2;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ProvaRemote {
    public static void main(String args[]) throws InterruptedException, RemoteException, NotBoundException {
        new Thread(
                new Correntista_Remote("iban_a", "iban_b", 'a')
        ).start();
        Thread.sleep(800);
        new Thread(
                new Correntista_Remote("iban_b", 'b')
        ).start();

    }
}
