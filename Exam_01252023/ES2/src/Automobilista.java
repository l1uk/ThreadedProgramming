public class Automobilista implements Runnable {

    GestioneParcheggioImpl gp;
    int id;

    public Automobilista(GestioneParcheggioImpl gp, int id) {
        this.id = id;
        this.gp = gp;
    }

    private void print(String s) {
        synchronized (System.out) {
            System.out.println(s);
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep((long) (100 + Math.random() * 1000));

            gp.ingresso();
            print(id + " Entrato");
            boolean parcheggiato = false;
            int piano_corrente = -1;
            Thread.sleep((long) (100 + Math.random() * 1000));
            while (!parcheggiato) {
                piano_corrente++;
                parcheggiato = true;
                try {
                    Thread.sleep((long) (100 + Math.random() * 1000));
                    gp.parcheggia(piano_corrente);
                } catch (GestioneParcheggio.PianoInesistente e) {
                    piano_corrente = 0;
                    parcheggiato = false;
                } catch (GestioneParcheggio.NoStalliLiberi e) {
                    parcheggiato = false;
                }
            }
            print(id + " Parcheggiato");
            Thread.sleep((long) (1000 + Math.random() * 10000));
            try {
                gp.partenza(piano_corrente);
            } catch (GestioneParcheggio.PianoInesistente e) {
                e.printStackTrace();
            }
            print(id + " Partito");
            Thread.sleep((long) (100 + Math.random() * 1000));
            gp.uscita();
            print(id + " Uscito");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
