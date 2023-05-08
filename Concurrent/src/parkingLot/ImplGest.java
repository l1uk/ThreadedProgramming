package parkingLot;

public class ImplGest implements GestioneParcheggio {

    final Piano[] parcheggio;
    final int numPiani;
    int numPosti;

    public ImplGest(int piani, int stalli) {
        parcheggio = new Piano[piani];
        for (int i = 0; i < piani; i++)
            parcheggio[i] = new Piano(stalli);
        numPiani = piani;
        numPosti = piani * stalli;

    }

    // operazioni a livello del parcheggio
    @Override
    public synchronized void ingresso() {
        // TODO Auto-generated method stub
        while (numPosti == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        numPosti--;
    }

    @Override
    public synchronized void uscita() {
        // TODO Auto-generated method stub
        numPosti++;
        notifyAll();
    }

    // operazioni a livello del singolo piano
    @Override
    public synchronized void parcheggia(int piano) throws PianoInesistente, NoStalliLiberi {
        // TODO Auto-generated method stub
        if (piano < 0 || piano >= numPiani)
            throw new PianoInesistente();
        parcheggio[piano].parcheggia();
    }

    @Override
    public synchronized void partenza(int piano) throws PianoInesistente {
        // TODO Auto-generated method stub
        if (piano < 0 || piano >= numPiani)
            throw new PianoInesistente();
        parcheggio[piano].partenza();
    }

}
