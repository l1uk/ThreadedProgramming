package ES2;

import java.rmi.Remote;
import java.rmi.RemoteException;

interface SistemaBancaRemoteInterface extends Remote {
    void nuovoConto(String idcc) throws RemoteException;

    int saldo(String idcc) throws ContoInesistente, RemoteException;

    void versamento(String idcc, int s)
            throws ContoInesistente, SommaNegativa, RemoteException;

    void prelievo(String idcc, int s)
            throws DisponibilitaInsufficiente, SommaNegativa, ContoInesistente, RemoteException;

    void trasferimento(String idccFrom, String idccTo, int s)
            throws DisponibilitaInsufficiente, SommaNegativa, ContoInesistente, RemoteException;

    boolean attendiTrasferimento(String idcc) throws ContoInesistente, RemoteException;

    class ContoInesistente extends Exception {
    }

    class SommaNegativa extends Exception {
    }

    class DisponibilitaInsufficiente extends Exception {
    }


}