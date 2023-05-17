package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GestioneParcheggioRemote extends Remote {

    public void setPianiStalli(int num_piani, int num_stalli_piano) throws RemoteException;

    void ingresso() throws RemoteException;

    void uscita() throws RemoteException;

    void parcheggia(int piano) throws PianoInesistente, NoStalliLiberi, RemoteException;

    void partenza(int piano) throws PianoInesistente, RemoteException;

    int postiLiberi() throws RemoteException;

    int stalliLiberi(int piano) throws PianoInesistente, RemoteException;

    class PianoInesistente extends Exception {
    }

    class NoStalliLiberi extends Exception {
    }
}

