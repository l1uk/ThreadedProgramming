package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ProduttoreMsgInterface_RMI extends Remote {
    void send(Msg_RMI m) throws DestinatarioPieno, RemoteException;

    class DestinatarioPieno extends Exception {
        int dest;

        public DestinatarioPieno(int dest) {
            this.dest = dest;
        }

        @Override
        public String getLocalizedMessage() {
            return "Recipient " + dest + " is full!!";
        }
    }
}
