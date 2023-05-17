package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class GestioneParcheggioRemoteImpl extends UnicastRemoteObject implements GestioneParcheggioRemote {


    public static void main(String[] args) throws RemoteException {
        GestioneParcheggioRemote stub = new GestioneParcheggioRemoteImpl();

        Registry r = LocateRegistry.createRegistry(1099);
        r.rebind("GestioneParcheggio", stub);

        System.out.println("Server ready");
    }

    public int num_piani = -1, num_stalli_piano;
    private int capienza_corrente;
    private int[] capienza_piano;

    public GestioneParcheggioRemoteImpl() throws RemoteException {
        super();
    }

    public void setPianiStalli(int num_piani, int num_stalli_piano) throws RemoteException {
        if (this.num_piani == -1) {

            this.num_piani = num_piani;
            this.num_stalli_piano = num_stalli_piano;
            this.capienza_corrente = num_piani * num_stalli_piano;
            this.capienza_piano = new int[num_piani];
            for (int i = 0; i < num_piani; i++) {
                this.capienza_piano[i] = num_stalli_piano;
            }
        }
    }

    @Override
    public synchronized void ingresso() {
        while (capienza_corrente <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        capienza_corrente--;
    }

    @Override
    public synchronized void uscita() {
        capienza_corrente++;
        notify(); // solo un automobilista deve essere "risvegliato", non tutti
    }

    @Override
    public synchronized void parcheggia(int piano) throws GestioneParcheggioRemote.PianoInesistente, GestioneParcheggioRemote.NoStalliLiberi {
        if (stalliLiberi(piano) <= 0) {
            throw new GestioneParcheggioRemote.NoStalliLiberi();
        }
        capienza_piano[piano]--;
    }

    @Override
    public void partenza(int piano) throws GestioneParcheggioRemote.PianoInesistente {
        stalliLiberi(piano);
        capienza_piano[piano]++;
    }

    @Override
    public int postiLiberi() {
        return capienza_corrente;
    }

    @Override
    public int stalliLiberi(int piano) throws GestioneParcheggioRemote.PianoInesistente {
        if (piano < 0 || piano >= num_piani) {
            throw new GestioneParcheggioRemote.PianoInesistente();
        }
        return capienza_piano[piano];
    }
}
