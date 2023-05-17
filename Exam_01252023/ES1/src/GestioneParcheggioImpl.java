public class GestioneParcheggioImpl implements GestioneParcheggio {

    public int num_piani, num_stalli_piano;
    private int capienza_corrente;
    private int[] capienza_piano;

    public GestioneParcheggioImpl(int num_piani, int num_stalli_piano) {
        this.num_piani = num_piani;
        this.num_stalli_piano = num_stalli_piano;
        this.capienza_corrente = num_piani * num_stalli_piano;
        this.capienza_piano = new int[num_piani];
        for (int i = 0; i < num_piani; i++) {
            this.capienza_piano[i] = num_stalli_piano;
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
    public synchronized void parcheggia(int piano) throws PianoInesistente, NoStalliLiberi {
        if (stalliLiberi(piano) <= 0) {
            throw new NoStalliLiberi();
        }
        capienza_piano[piano]--;
    }

    @Override
    public void partenza(int piano) throws PianoInesistente {
        stalliLiberi(piano);
        capienza_piano[piano]++;
    }

    @Override
    public int postiLiberi() {
        return capienza_corrente;
    }

    @Override
    public int stalliLiberi(int piano) throws PianoInesistente {
        if (piano < 0 || piano >= num_piani) {
            throw new PianoInesistente();
        }
        return capienza_piano[piano];
    }
}
