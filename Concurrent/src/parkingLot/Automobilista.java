package parkingLot;

public class Automobilista extends Thread {
    private final int id;
    final GestioneParcheggio GP;

    public Automobilista(int id, GestioneParcheggio gP) {
        super();
        this.id = id;
        GP = gP;
    }

    public void run() {
        int i = 0;
        try {

            System.out.println(id + " Provo a entrare");
            GP.ingresso();
            System.out.println(id + " Entrato");
            i = 0;
            while (true) {
                try {
                    System.out.println(id + " Provo a parcheggiare a piano " + i);
                    GP.parcheggia(i);
                    System.out.println(id + " Parcheggiato a piano" + i);
                    break;
                } catch (NoStalliLiberi | PianoInesistente e) {
                    if (e instanceof NoStalliLiberi) {
                        System.out.println();
                        Thread.sleep(500);
                        i++;
                    } else {
                        i = 0;
                    }
                }
            }
            try {
                Thread.sleep(6000 + (long) (Math.random() * 10000));
                GP.partenza(i);
                System.out.println(id + " Uscito dal piano " + i);

            } catch (PianoInesistente e) {
                e.printStackTrace();
            }
            GP.uscita();
            System.out.println(id + " Uscito");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
